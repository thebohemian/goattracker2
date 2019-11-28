package com.github.thebohemian.libgoattracker

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

interface LibGoat2 : Library
{
	fun start_goattracker(argc: Int, argv: Array<String>)

	/** Convenience method for more kotlinesque invocation */
	fun start_goattracker(argc: Int, argv: List<String>) {
		start_goattracker(argc, argv.toTypedArray())
	}

	companion object {
		private val LIBRARY_NAME = "goat2"

		fun load() = Native.loadLibrary(LIBRARY_NAME, LibGoat2::class.java)
	}
}
