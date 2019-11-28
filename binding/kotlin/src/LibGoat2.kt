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

	fun get_instrument_pointer(instrumentNumber: Int): NativeInstrumentReference

	/** Convenience method for more kotlinesque invocation */
	fun start_goattracker(argc: Int, argv: List<String>) =
		start_goattracker(argc, argv.toTypedArray())

	companion object {
		const val MAX_STR = 32

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

