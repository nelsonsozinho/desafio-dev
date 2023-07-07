package com.shire42.cnab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication()
class CnabApplication

fun main(args: Array<String>) {
	configureApplication(SpringApplicationBuilder()).run(*args)
}

fun configureApplication(builder: SpringApplicationBuilder): SpringApplicationBuilder {
	return builder.sources(CnabApplication::class.java)
}


