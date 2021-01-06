package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        // getStringExtra 메소드를 사용해서 인텐트에 저장된 정수 데이터를 가져 온다.
        val height=intent.getIntExtra("height",0)
        val weight=intent.getIntExtra("weight",0)
        // BMI 계산
        val bmi:Int =(weight/Math.pow(height/100.0, 2.0)).toInt()
        // height기준으로 정상 체중 계산
        val normalw:Int =(22*Math.pow(height/100.0, 2.0)).toInt()
        // 결과 표시
        when{
            bmi >= 35-> resultTextView.text="고도 비만"
            bmi >= 30-> resultTextView.text="2단계 비만"
            bmi >= 25-> resultTextView.text="1단계 비만"
            bmi >= 23-> resultTextView.text="과체중"
            bmi >=18.5-> resultTextView.text="정상"
            else -> resultTextView.text="저체중"
        }
        // 이미지 및 텍스트 표시
        when{
            bmi>=23->{
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24)
                description.setText("현재 ${bmi}이며 정상 수치까지 -${bmi-22}(-${weight-normalw}kg)입니다.")}
            bmi>=18.5->{
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24)
                description.setText("현재 ${bmi}입니다.")}
            else->{
                imageView.setImageResource(R.drawable.ic_baseline_sentiment_dissatisfied_24)
                description.setText("현재 ${bmi}이며 정상 수치까지 +${22-bmi}(+${normalw-weight}kg)입니다.")}
        }
    }
}