package com.github.thebohemian.libgoat2.model

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

class CommandSerializer : KSerializer<Instrument.WaveTable.Command> {

    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("note") {
        init {
            addElement("type")
            addElement("frames")
            addElement("triangle")
            addElement("sawtooth")
            addElement("pulse")
            addElement("noise")
            addElement("gate")
            addElement("hardSync")
            addElement("ringMod")
            addElement("resetOscillator")
            addElement("note")
        }
    }

    override fun deserialize(decoder: Decoder): Instrument.WaveTable.Command {
        var type: String? = null
        var frames: Int? = null
        var triangle: Boolean = false
        var sawtooth: Boolean = false
        var pulse: Boolean = false
        var noise: Boolean = false
        var gate: Boolean = false
        var hardSync: Boolean = false
        var ringMod: Boolean = false
        var resetOscillator: Boolean = false
        var note: Instrument.WaveTable.Command.Wave.Note = Instrument.WaveTable.Command.Wave.Note.NoChange

        decoder.beginStructure(descriptor).apply {

            loop@ while (true) {
                when (val i = decodeElementIndex(descriptor)) {
                    CompositeDecoder.READ_DONE -> break@loop
                    0 -> type = decodeStringElement(descriptor, i)
                    1 -> frames = decodeIntElement(descriptor, i)
                    2 -> triangle = decodeBooleanElement(descriptor, i)
                    3 -> sawtooth = decodeBooleanElement(descriptor, i)
                    4 -> pulse = decodeBooleanElement(descriptor, i)
                    5 -> noise = decodeBooleanElement(descriptor, i)
                    6 -> gate = decodeBooleanElement(descriptor, i)
                    7 -> hardSync = decodeBooleanElement(descriptor, i)
                    8 -> ringMod = decodeBooleanElement(descriptor, i)
                    9 -> resetOscillator = decodeBooleanElement(descriptor, i)
                    10 -> note = decodeSerializableElement(descriptor, i, NoteSerializer())
                    else -> throw SerializationException("Unknown index $i")
                }
            }
            endStructure(descriptor)
        }

        return when (type) {
            "delay" -> Instrument.WaveTable.Command.Delay(frames
                    ?: throw SerializationException("Missing frames values"))
            "wave" -> Instrument.WaveTable.Command.Wave(
                    triangleOn = triangle,
                    sawtoothOn = sawtooth,
                    pulseOn = pulse,
                    noiseOn = noise,
                    gate = gate,
                    hardSync = hardSync,
                    ringMod = ringMod,
                    resetOscillator = resetOscillator,
                    note = note)
            else -> throw SerializationException("Invalid or missing type")
        }
    }

    override fun serialize(encoder: Encoder, obj: Instrument.WaveTable.Command) {
        when (obj) {
            is Instrument.WaveTable.Command.Delay ->
                encoder.beginStructure(descriptor).apply {
                    encodeStringElement(descriptor, 0, "delay")
                    encodeIntElement(descriptor, 1, obj.frames)

                    endStructure(descriptor)
                }
            is Instrument.WaveTable.Command.Wave -> {
                encoder.beginStructure(descriptor).apply {
                    encodeStringElement(descriptor, 0, "wave")
                    encodeBooleanElementWhenSet(descriptor, 2, obj.triangleOn)
                    encodeBooleanElementWhenSet(descriptor, 3, obj.sawtoothOn)
                    encodeBooleanElementWhenSet(descriptor, 4, obj.pulseOn)
                    encodeBooleanElementWhenSet(descriptor, 5, obj.noiseOn)
                    encodeBooleanElementWhenSet(descriptor, 6, obj.gate)
                    encodeBooleanElementWhenSet(descriptor, 7, obj.hardSync)
                    encodeBooleanElementWhenSet(descriptor, 8, obj.ringMod)
                    encodeBooleanElementWhenSet(descriptor, 9, obj.resetOscillator)
                    encodeSerializableElement(descriptor, 10, NoteSerializer(), obj.note)
                    endStructure(descriptor)
                }
            }
            else -> throw SerializationException("Unexpected type")
        }
    }

}

fun CompositeEncoder.encodeBooleanElementWhenSet(descriptor: SerialDescriptor, index: Int, value: Boolean) {
    if (value) {
        encodeBooleanElement(descriptor, index, value)
    }
}