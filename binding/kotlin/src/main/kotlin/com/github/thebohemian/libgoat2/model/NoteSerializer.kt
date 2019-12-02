package com.github.thebohemian.libgoat2.model

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

class NoteSerializer : KSerializer<Instrument.WaveTable.Command.Wave.Note> {

    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("note") {
        init {
            addElement("type")
            addElement("note")
            addElement("halfTones")
        }
    }

    override fun deserialize(decoder: Decoder): Instrument.WaveTable.Command.Wave.Note {
        var type: String? = null
        var note: String? = null
        var halfTones: Int? = null

        decoder.beginStructure(descriptor).apply {

            loop@ while (true) {
                when (val i = decodeElementIndex(descriptor)) {
                    CompositeDecoder.READ_DONE -> break@loop
                    0 -> type = decodeStringElement(descriptor, i)
                    1 -> note = decodeStringElement(descriptor, i)
                    2 -> halfTones = decodeIntElement(descriptor, i)
                    else -> throw SerializationException("Unknown index $i")
                }
            }
            endStructure(descriptor)
        }

        return when (type) {
            "n/a" -> Instrument.WaveTable.Command.Wave.Note.NoChange
            "abs" -> Instrument.WaveTable.Command.Wave.Note.AbsoluteNote(note
                    ?: throw SerializationException("Missing absolute note"))
            "rel" -> Instrument.WaveTable.Command.Wave.Note.RelativeNote(halfTones
                    ?: throw SerializationException("Missing half tones value"))
            else -> throw SerializationException("Invalid or missing type")
        }
    }

    override fun serialize(encoder: Encoder, obj: Instrument.WaveTable.Command.Wave.Note) {
        when (obj) {
            is Instrument.WaveTable.Command.Wave.Note.NoChange ->
                encoder.beginStructure(descriptor).apply {
                    encodeStringElement(descriptor, 0, "n/a")

                    endStructure(descriptor)
                }
            is Instrument.WaveTable.Command.Wave.Note.AbsoluteNote -> {
                encoder.beginStructure(descriptor).apply {
                    encodeStringElement(descriptor, 0, "abs")
                    encodeStringElement(descriptor, 1, obj.note)

                    endStructure(descriptor)
                }
            }
            is Instrument.WaveTable.Command.Wave.Note.RelativeNote -> {
                encoder.beginStructure(descriptor).apply {
                    encodeStringElement(descriptor, 0, "rel")
                    encodeIntElement(descriptor, 1, obj.halfTones)

                    endStructure(descriptor)
                }
            }
        }
    }

}