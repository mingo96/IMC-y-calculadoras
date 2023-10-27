package com.example.IMCycalculadoras.calculadoraJavi

/**
 * Calcula las operaciones matemáticas básicas
 */
class Calculo(
    /**
     * primer número de la operación
     */
    var num1: Float = 0f,
    /**
     * segundo número de la operación
     */
    var num2: Float = 0f,
    /**
     * Operación realizada
     * 0: Suma
     * 1: Resta
     * 2: Multiplicación
     * 3: División
     */
    var operacion: Int = 0
) {
    /**
     * Resultado de la operación
     */
    var resultado: Float = 0.0f

    /**
     * Verifica que si ya estamos editando la parte decimal de un número o no
     */
    var enDecimal = false

    /**
     * Método general que llama a otros para calcular las operaciones
     */
    fun calcular() {
        when (operacion) {
            0 -> sumar(this.num1, this.num2)
            1 -> restar(this.num1, this.num2)
            2 -> multiplicar(this.num1, this.num2)
            3 -> dividir(this.num1, this.num2)
        }
    }

    /**
     * Operación básica de suma
     */
    private fun sumar(num1: Float, num2: Float) {
        this.resultado = num1 + num2
    }
    /**
     * Operación básica de resta
     */
    private fun restar(num1: Float, num2: Float) {
        this.resultado = num1 - num2
    }
    /**
     * Operación básica de multiplicación
     */
    private fun multiplicar(num1: Float, num2: Float) {
        this.resultado = num1 * num2
    }
    /**
     * Operación básica de división
     */
    private fun dividir(num1: Float, num2: Float) {
        this.resultado = num1 / num2
    }

    /**
     * Limpiar el objeto para realizar otro cálculo
     */
    fun clear() {
        num1 = 0f
        num2 = 0f
        operacion = 0
        resultado = 0f
        enDecimal = false
    }
}
