package com.github.thebohemian.libgoat2.example

import com.charleskorn.kaml.Yaml
import com.github.thebohemian.libgoat2.mapper.YamlMapper
import com.github.thebohemian.libgoat2.model.Instrument

fun main(args: Array<String>) {
    val inst = Instrument(
            name = "Testinstrument",
            parameter = Instrument.Parameter(
                    attack = 5,
                    decay = 5,
                    sustain = 15,
                    release = 10,
                    vibratoDelay = 0,
                    gateTimer = 2,
                    firstFrameWave = 9
            ),
            waveTable = Instrument.WaveTable(
                    entries = listOf(
                            Instrument.WaveTable.Command.Wave(
                                    triangleOn = true,
                                    gate = true
                            ),
                            Instrument.WaveTable.Command.Wave(
                                    triangleOn = true,
                                    gate = false
                            )
                    ),
                    jumpToEntry = 0
            )
    )

    val yamlMapper = YamlMapper(Yaml.default)
    println("mapped instrument: \n${yamlMapper(inst)}")
}
