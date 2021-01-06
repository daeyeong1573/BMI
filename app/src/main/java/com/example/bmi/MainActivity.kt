package com.example.bmi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    /* 쉐어드 프리퍼런스 저장 메소드:
       쉐어드 프리퍼런스는 App에 포함되는 데이터 파일을 만들어서 디바이스에 저장하거나 관리하는 객체이다.
       데이터 파일을 편집할 때는 에디터 객체를 불러와야 한다.
        (덕분에 클래스 외부로 데이터를 보내거나, 외부 데이터를 불러올 수 있음)
    */
    private fun saveData(height:String,weight:String){
        val pref = this.getPreferences(0)
        val editor=pref.edit()
        // 키와 밸류를 쌍으로 저장하고 apply한다
        editor.putString("KEY_HEIGHT",height)
            .putString("KEY_WEIGHT",weight)
            .apply()
    }
    // 쉐어드 프리퍼런스 로드 메소드
    private fun loadData(){
        val pref=this.getPreferences(0)
        // 키에 해당되는 밸류를 가져오는데 저장된 값이 없으면 ""을 가져온다
        val height=pref.getString("KEY_HEIGHT","")
        val weight=pref.getString("KEY_WEIGHT","")
        if(!height.equals("") && !height.equals("")){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 쉐어드 프리퍼런스 이용해서 editText 데이터 세팅
        loadData()
        resultButton.setOnClickListener{
            // editText데이터 불러와서 정수형으로 만들고 저장
            val w:String = weightEditText.text.toString()
            val h:String = heightEditText.text.toString()
            // 값을 가져 왔는데 비어있지 않을 때 if문 성립
            if(!w.equals("") && !h.equals("")){
                // 쉐어드 프리퍼런스 이용해서 영구적으로 데이터 저장
                saveData(h,w)
                // anko라이브러리 사용해서 액티비티 이동 및 인텐트에 데이터 저장 쉽게 해결
                startActivity<ResultActivity>("weight" to w.toInt(), "height" to h.toInt())}
            else
                toast("키와 몸무게를 입력해주세요!")
        }
    }
}