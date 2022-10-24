package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var op1 = 0.0
    private var op2 = 0.0
    private var op1Binario = 0
    private var op2Binario = 0
    private var operacion = 0
    private var esbinario = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultadoTextView.text = "0"

        num0.setOnClickListener{ numeroPresionado(digito= "0")}
        num1.setOnClickListener{ numeroPresionado(digito= "1")}
        num2.setOnClickListener{ numeroPresionado(digito= "2")}
        num3.setOnClickListener{ numeroPresionado(digito= "3")}
        num4.setOnClickListener{ numeroPresionado(digito= "4")}
        num5.setOnClickListener{ numeroPresionado(digito= "5")}
        num6.setOnClickListener{ numeroPresionado(digito= "6")}
        num7.setOnClickListener{ numeroPresionado(digito= "7")}
        num8.setOnClickListener{ numeroPresionado(digito= "8")}
        num9.setOnClickListener{ numeroPresionado(digito= "9")}
        coma.setOnClickListener { numeroPresionado(digito=".") }

        reset.setOnClickListener { borrarTodo() }

        suma.setOnClickListener { operacionPresionada(operador="+") }
        resta.setOnClickListener { operacionPresionada(operador="-") }
        multiplicacion.setOnClickListener { operacionPresionada(operador="*") }
        division.setOnClickListener { operacionPresionada(operador="/") }
        igual.setOnClickListener { cuenta(operacion) }

        binario.setOnClickListener { modoBinario() }
        decimal.setOnClickListener { modoDecimal() }
    }

    private fun modoBinario(){
        esbinario=true
        resultadoTextView.text = Integer.toBinaryString(Integer.parseInt(resultadoTextView.text.toString()))
    }

    private fun modoDecimal(){
        esbinario=false
        resultadoTextView.text = (Integer.parseInt(resultadoTextView.text.toString() ,2)).toString()
    }

    private fun numeroPresionado(digito:String){
        if(resultadoTextView.text =="0" && digito != "."){
            if(esbinario){
                resultadoTextView.text = Integer.toBinaryString(Integer.parseInt("$digito"))
            } else resultadoTextView.text = "$digito"
        } else {
            if(esbinario){

                resultadoTextView.text = Integer.toBinaryString(Integer.parseInt("${(Integer.parseInt(resultadoTextView.text.toString() ,2)).toString()}$digito"))
            }
            else
                resultadoTextView.text = "${resultadoTextView.text}$digito"
        }
        determinarOperador()
    }

    private fun operacionPresionada(operador:String){
        if(operador=="+"){
            operacion=1
        }else if(operador=="-"){
            operacion=2
        }else if(operador=="*"){
            operacion=3
        }else if(operador=="/"){
            operacion=4
        }
        resultadoTextView.text="0"

    }

    private fun determinarOperador(){
        if(operacion==0){
            if (esbinario){
                op1Binario=(Integer.parseInt(resultadoTextView.text.toString() ,2)).toString().toInt()
            }else
                op1=resultadoTextView.text.toString().toDouble()
        } else
            if(esbinario){
                op2Binario=(Integer.parseInt(resultadoTextView.text.toString() ,2)).toString().toInt()
            } else
                op2=resultadoTextView.text.toString().toDouble()
    }

    private fun cuenta(operador:Int){
        if (operador==0){
            if(esbinario){
                resultadoTextView.text=(Integer.toBinaryString(Integer.parseInt(op1Binario.toString())).toInt()).toString()
            } else resultadoTextView.text=op1.toString()
        }
        if (operador==1){
            if(esbinario){
                op1Binario = op1Binario + op2Binario
                op1Binario=Integer.toBinaryString(Integer.parseInt(op1Binario.toString())).toInt()
                resultadoTextView.text=op1Binario.toString()
                //resultadoTextView.text = if("$op1Binario".endsWith(".0")) { "$op1Binario".replace(".0","") } else { "%.2f".format(op1Binario) }
            } else {
                op1= op1+op2
                resultadoTextView.text = if("$op1".endsWith(".0")) { "$op1".replace(".0","") } else { "%.2f".format(op1) }
            }
        } else if(operador==2){
            if(esbinario){
                op1Binario = op1Binario - op2Binario
                op1Binario=Integer.toBinaryString(Integer.parseInt(op1Binario.toString())).toInt()
                resultadoTextView.text=op1Binario.toString()
            } else {
                op1= (op1-op2)
                resultadoTextView.text = if("$op1".endsWith(".0")) { "$op1".replace(".0","") } else { "%.2f".format(op1) }
            }
        } else if(operador==3){
            if(esbinario){
                op1Binario = op1Binario * op2Binario
                op1Binario=Integer.toBinaryString(Integer.parseInt(op1Binario.toString())).toInt()
                resultadoTextView.text=op1Binario.toString()
            } else {
                op1= op1*op2
                resultadoTextView.text = if("$op1".endsWith(".0")) { "$op1".replace(".0","") } else { "%.2f".format(op1) }
            }
        } else if(operador==4){
            if(esbinario){
                op1Binario = op1Binario / op2Binario
                op1Binario=Integer.toBinaryString(Integer.parseInt(op1Binario.toString())).toInt()
                resultadoTextView.text=op1Binario.toString()
            } else {
                op1= op1/op2
                resultadoTextView.text = if("$op1".endsWith(".0")) { "$op1".replace(".0","") } else { "%.2f".format(op1) }
            }
        }
        operacion=0
    }

    private fun borrarTodo(){
        resultadoTextView.text = "0"
        op1 = 0.0
        op2 = 0.0
        op1Binario=0
        op2Binario=0
    }


}