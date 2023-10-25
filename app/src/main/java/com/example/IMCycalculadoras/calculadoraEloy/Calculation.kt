package com.example.IMCycalculadoras.calculadoraEloy

/**
 * @constructor no queremos constructor, todos los valores son predeterminados
 * */
class Calculation {

    /**
     * @property num1 alberga los numeros antes del [operation]
     * */
    private var num1 = ""
    /**
     * @property num2 alberga los numeros despues del [operation]
     * */
    private var num2 = ""

    /**
     * @property resolution guarda el resultado
     * */
    private var resolution = "0"

    /**
     * @property operation alberga el operador que se va a usar
     * */
    private var operation = ""

    /**
     * @property state es una variable compleja, to do funciona alrededor de ella, segun el numero
     * que albergue las cosas hacen una cosa u otra
     * */
    private var state = 1

    /**
     * @property operators tiene los operadores, para no repetir la lista en varios puntos
     */
    private val operators = arrayOf("+","-","*","/")

    /**
     * screenChange se ejecuta cada vez que se pulsa un boton, recibe este por pantalla
     * mediante [pressedButton], como to do se procesa por texto, da igual que sea string
     * @param pressedButton es la cadena que devuelve un boton al ser pulsado
     * */
    fun screenChange(pressedButton:String){

         //*arreglo de error* creamos [state] 7 para el caso de que tengamos mas numeros de los
         //permitidos, este devolverá una cadena de error, cualdo pulsen el siguiente boton
         //(el codigo de aqui) reiniciamos y sigue con normalidad
        if (state == 7){
            clear()
        }

        //este when redirecciona [state] segun el boton presionado y la situación

        when(pressedButton){

             //[state] pasa a ser 3 si el [pressedButton] es un [operators] Y no estamos en [state]
             //5, ya que esto significa que acabamos de pulsar =, en caso de que lo sea se
             //comprobará mas tarde y se hará lo pertinente

            in operators->{
                if (state!=5)
                state = 3
            }

             //si el [pressedButton] es = y no estamos en [state] 1 (escribir primer numero) ni en
             //[state] 3 (acabamos de presionar un operador), entramos a estado 4
             //tampoco cambiamos de [state] si ya estamos en 2 pero [num2] esta vacío

            "="->{
                if (state!=1 && state!= 3 && (num2.isNotEmpty() && state==2))
                state = 4
            }

             //si el [pressedButton] es CE entramos a [state] 6

            "CE"->{
                state = 6
            }
        }


         //cada [state] representa una cosa diferente,
         //1 y 2 representan estar recibiendo numeros para [num1] y [num2], respectivamente
         //3 representa haber recibido un [operation]
         //4 representa haber pulsado =
         //5 representa que acabamos de pulsar un boton despues de = y no ha sido un numero
         //6 representa que hemos pulsado CE

        when(state){
            in 1..2->{

                 //comprueba que el [pressedButton] no es =, para evitar erratas, sobre to do
                 //cuando [state] es 1

                if (pressedButton != "=")
                addNumber(pressedButton)
            }
            3->{

                 //si [num1] no esta vacío, podemos guardar el [operation] y pasar a [state] 2, en caso
                 //contrario volvemos a [state] 1

                if (num1.isNotEmpty()){
                    operation = pressedButton
                    state = 2
                } else state = 1
            }
            4->{
                calculate()
            }
            5->if(pressedButton!= "="){

                 //reasignamos valores, si el [pressedButton] esta en [operators] se lo damos a [operation]
                 //y pasamos a [state] 2, si es = no hacemos nada, si es numero limpiamos y añadimos
                 //el [pressedButton]
                 //nada de esto pasa si han vuelto a presionar =

                num1 = resolution
                num2 = ""
                if (pressedButton in operators) {
                    operation = pressedButton
                    state = 2
                }
                else {
                    clear()
                    addNumber(pressedButton)
                }
            }
            6->{
                clear()
            }

        }
    }

    /**
     * se ejecuta con cada pulsacion de boton para devolver lo que debe haber en la linea de texto,
     * segun el [state] devuelve un valor u otro, en caso de que se encuentre otro [state] lo
     * devuelve para averiguar cual es (y porque el when tiene que tener un else)
     * @return devuelve la cadena correspondiente
     * */
    fun returnActualText():String{
        return when(state){
            1->{
                num1
            }
            2->{
                num1 + operation + num2
            }
            5->{
                if("$num1$operation$num2=$resolution".length > 13) "$num1$operation$num2=\n$resolution" else "$num1$operation$num2=$resolution"
            }
            7->{
                "demasiados numeros"
            }
            else->{
                "$state"
            }
        }
    }

    /**
     * actualiza un numero con el añadido, aprovechando los [state] es la misma funcion
     * para los dos casos
     * @param added representa el numero a añadir
     * */
    private fun addNumber(added : String){
        //solo ponemos el punto si aun no lo hay
        when(state){
            1->{
                if((added == "." && !num1.contains(".") || added!="."))num1 +=added
                if(added == "<") num1 = num1.dropLast(2)
            }
            2->{

                if((added == "." && !num2.contains(".") || added!="."))num2 +=added

                if(added == "<") num2 = num2.dropLast(2)

                //si num2 acaba estando vacio volvemos a estado 1
                if(num2.isEmpty()){
                    state = 1
                }
            }
        }
    }

    /**
     * segun la [operation] que tengamos guardada llama a una funcion de calculo o a otra y
     * actualiza el [state] a 5 para que al pulsar el siguiente boton pasemos a otra operacion
     * esto lo hacemos dentro de un try & catch para en caso de que la cuenta sea demasiado grande
     * (error de tamaño o de cuentas) demos [state] 7
     * */
    private fun calculate(){
        try {
            when (operation) {
                "+" -> {
                    sum()
                }

                "-" -> {
                    substract()
                }

                "*" -> {
                    multiply()
                }

                "/" -> {
                    divide()
                }

            }

            state = 5
            if((resolution.length>9 && !resolution.contains("."))||resolution.contains("E"))
                throw Exception()

            if (resolution.contains(".") && resolution.split(".")[1].length>=3) {
                resolution = resolution.split(".").first()+"."+resolution.split(".")[1].substring(0..2)
            }

        }catch (e:Exception){
            state = 7
        }

    }

    /**
     * funciones de calculo, bastante descriptivas, pasan [num1] y [num2] a Int para hacer cuentas
     * con ellas y asignar el resultado a [resolution]
     * en todos menos en division, pasamos a double para operar solo si hay algun .
     * */

    private fun sum(){
        resolution = if (num1.contains(".")||num2.contains("."))
            (num1.toDouble() + num2.toDouble()).toString()
        else
            (num1.toInt()+num2.toInt()).toString()
    }

    private fun substract(){
        resolution = if (num1.contains(".")||num2.contains("."))
            (num1.toDouble() - num2.toDouble()).toString()
        else
            (num1.toInt()-num2.toInt()).toString()
    }

    private fun multiply(){
        resolution = if (num1.contains(".")||num2.contains("."))
            (num1.toDouble() * num2.toDouble()).toString()
        else
            (num1.toInt()*num2.toInt()).toString()
    }

    private fun divide(){
        resolution =(num1.toDouble() / num2.toDouble()).toString()
    }

    /**
     * lleva a estado base, se ejecuta cuando pulsamos CE
     * */
    private fun clear(){
        num1 = ""
        num2 = ""
        resolution = ""
        state = 1
    }


}