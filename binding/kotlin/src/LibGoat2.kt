package com.github.thebohemian.libgoattracker

import java.nio.charset.StandardCharsets

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer
import com.sun.jna.Structure

interface LibGoat2 : Library
{
	fun start_goattracker(argc: Int, argv: Array<String>): Int

	fun stop_goattracker()

	fun play_song()

	fun stop_song()

	fun load_binary_song(filename: String)

	fun get_instrument(instrumentNumber: Int): NativeInstrumentReference

	fun get_left_table(index: Int): ParameterTableReference

	fun get_right_table(index: Int): ParameterTableReference

	fun get_pattern(index: Int): PatternReference

	fun get_pattern_lengths(): PatternLengthsReference

	fun get_songorder(songIndex: Int, channelIndex: Int): SongOrderReference

	fun get_song_lengths(): SongLengthsReference

	fun get_song_name(): StringReference

	fun get_author_name(): StringReference

	fun get_copyright_name(): StringReference

	/** Convenience method for more kotlinesque invocation */
	fun start_goattracker(argc: Int, argv: List<String>) =
		start_goattracker(argc, argv.toTypedArray())

	companion object {
		const val MAX_STR = 32

		const val MAX_CHN = 3

		const val MAX_TABLES = 4

		const val MAX_TABLE_LEN = 255

		const val MAX_PATT = 208

		const val MAX_PATT_ROWS = 128

		const val MAX_PATT_ROWS_DATA = MAX_PATT_ROWS * 4 + 4

		const val MAX_SONGLEN = 254

		const val MAX_SONGLEN_DATA = MAX_SONGLEN + 2

		private val LIBRARY_NAME = "goat2"

		fun load() = Native.loadLibrary(LIBRARY_NAME, LibGoat2::class.java)
	}
}

class NativeInstrumentReference : Structure(), Structure.ByReference {

	@JvmField var ad: Byte = 0
	@JvmField var sr: Byte = 0

	@JvmField var wavetableLine: Byte = 0
	@JvmField var pulsetableLine: Byte = 0
	@JvmField var filtertableLine: Byte = 0
	@JvmField var speedtableLine: Byte = 0

	@JvmField var vibdelay: Byte = 0
	@JvmField var gatetimer: Byte = 0
	@JvmField var firstwave: Byte = 0

	@JvmField var _name = ByteArray(LibGoat2.MAX_STR)

	var name: String 
		get() = _name.toString(StandardCharsets.UTF_8)
		set(name) {
			name.toByteArray().let { it.copyInto(_name, 0, 0, Math.min(_name.size, it.size)) }
		}

	override fun getFieldOrder() = listOf("ad", "sr", "wavetableLine", "pulsetableLine", "filtertableLine", "speedtableLine", "vibdelay", "gatetimer", "firstwave", "_name")

}

class ParameterTableReference : Structure(), Structure.ByReference {

	@JvmField var bytes = ByteArray(LibGoat2.MAX_TABLE_LEN)

	fun setByte(index: Int, value: Int) { bytes[index] = value.toByte() }

	override fun getFieldOrder() = listOf("bytes")

}

class StringReference : Structure(), Structure.ByReference {

	@JvmField var _text = ByteArray(LibGoat2.MAX_STR)

	override fun getFieldOrder() = listOf("_text")

	var text: String 
		get() = _text.toString(StandardCharsets.UTF_8)
		set(text) {
			text.toByteArray().let { it.copyInto(_text, 0, 0, Math.min(_text.size, it.size)) }
		}
}

class PatternReference : Structure(), Structure.ByReference {

	@JvmField var bytes = ByteArray(LibGoat2.MAX_PATT_ROWS_DATA)

	fun setByte(index: Int, value: Int) { bytes[index] = value.toByte() }

	override fun getFieldOrder() = listOf("bytes")

}

class PatternLengthsReference : Structure(), Structure.ByReference {

	@JvmField var lengths = IntArray(LibGoat2.MAX_PATT)

	override fun getFieldOrder() = listOf("lengths")

}

class SongOrderReference : Structure(), Structure.ByReference {

	@JvmField var bytes = ByteArray(LibGoat2.MAX_SONGLEN_DATA)

	fun setByte(index: Int, value: Int) { bytes[index] = value.toByte() }

	override fun getFieldOrder() = listOf("bytes")

}

class SongLengthsReference : Structure(), Structure.ByReference {

	@JvmField var lengths = IntArray(LibGoat2.MAX_CHN)

	override fun getFieldOrder() = listOf("lengths")

}

