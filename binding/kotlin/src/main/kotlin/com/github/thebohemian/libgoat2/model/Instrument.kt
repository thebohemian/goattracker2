package com.github.thebohemian.libgoat2.model

import kotlinx.serialization.Serializable

@Serializable
data class Instrument(
        val name: String,
        val parameter: Parameter,
        val waveTable: WaveTable) {

    @Serializable
    data class Parameter(
            val attack: Int,
            val decay: Int,
            val sustain: Int,
            val release: Int,
            val vibratoDelay: Int,
            val gateTimer: Int,
            val firstFrameWave: Int)

    @Serializable
    data class WaveTable(
            val entries: List<Command>,
            val jumpToEntry: Int) {

        @Serializable(with = CommandSerializer::class)
        sealed class Command {

            data class Delay(val frames: Int) : Command()

            data class Wave(
                    val triangleOn: Boolean = false,
                    val sawtoothOn: Boolean = false,
                    val pulseOn: Boolean = false,
                    val noiseOn: Boolean = false,
                    val gate: Boolean = false,
                    val hardSync: Boolean = false,
                    val ringMod: Boolean = false,
                    val resetOscillator: Boolean = false,
                    val note: Note = Note.NoChange
            ) : Command() {

                @Serializable(with = NoteSerializer::class)
                sealed class Note {
                    data class AbsoluteNote(val note: String) : Note()

                    data class RelativeNote(val halfTones: Int) : Note()

                    object NoChange : Note()
                }
            }
        }

    }

}