package br.com.itau.bootcamp.service

import br.com.itau.bootcamp.dto.DepositarRequest
import br.com.itau.bootcamp.dto.DepositarResponse
import br.com.itau.bootcamp.dto.SaqueRequest
import br.com.itau.bootcamp.dto.saqueResponse
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class servicoMonetarioService {

    @Inject
    lateinit var dadosConta: listaConta

    fun realizaDeposito (contas: DepositarRequest): DepositarResponse {

        val encontrarConta = dadosConta.buscaConta(contas.conta)

        if (encontrarConta.conta > 0 && encontrarConta.CPF == contas.CPF) {
            encontrarConta.saldo += contas.valorDeposito
        } else {
//                 throw ExceptionHandler("Conta ${contas.conta} não encontrada")
            throw Exception("Conta ${contas.conta} não encontrada")
        }

        val retorno = DepositarResponse(CPF = contas.CPF, conta = contas.conta, saldo = encontrarConta.saldo.setScale(2))
        return retorno

    }

    fun realizaSaque(saque: SaqueRequest): saqueResponse {

        val encontrarConta = dadosConta.buscaConta(saque.conta)

        if (encontrarConta.conta > 0 && encontrarConta.CPF == saque.CPF && encontrarConta.saldo >= saque.valorSaque) {
            encontrarConta.saldo -= saque.valorSaque
        } else {
//                 throw ExceptionHandler("Conta ${contas.conta} não encontrada")
            throw Exception("Conta ${saque.conta} não encontrada")
        }

        val retorno = saqueResponse(CPF = saque.CPF, conta = saque.conta, saldo = encontrarConta.saldo.setScale(2))
        return retorno
    }
}
