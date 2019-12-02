package com.github.thebohemian.libgoat2.mapper

import com.charleskorn.kaml.Yaml
import com.github.thebohemian.libgoat2.model.Instrument

class YamlMapper (
        private val yaml: Yaml) {

    operator fun invoke(instrument: Instrument) = yaml.stringify(Instrument.serializer(), instrument)

}
