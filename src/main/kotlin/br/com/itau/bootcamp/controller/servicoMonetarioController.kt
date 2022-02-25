package br.com.itau.bootcamp.controller

import br.com.itau.bootcamp.dto.DepositarRequest
import br.com.itau.bootcamp.dto.DepositarResponse
import br.com.itau.bootcamp.dto.SaqueRequest
import br.com.itau.bootcamp.dto.saqueResponse
import br.com.itau.bootcamp.service.servicoMonetarioService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import java.time.LocalDateTime
import javax.validation.Valid

@Controller()
@Validated
open class DepositoController(private val servicoMonetario: servicoMonetarioService
) {

    @Post("/deposito")
    fun depositar(@Body @Valid deposito: DepositarRequest): io.micronaut.http.HttpResponse<DepositarResponse> {
        println("Aplicacao Transferencias [Deposito] inicializada -> Post [[${LocalDateTime.now()}]]")
        return io.micronaut.http.HttpResponse.created(servicoMonetario.realizaDeposito(deposito))
    }

    @Post("/saque")
    fun sacar(@Body @Valid saque: SaqueRequest): io.micronaut.http.HttpResponse<saqueResponse> {
        println("Aplicacao Transferencias [Saque] inicializada -> Post [[${LocalDateTime.now()}]]")
        return io.micronaut.http.HttpResponse.created(servicoMonetario.realizaSaque(saque))
    }

}