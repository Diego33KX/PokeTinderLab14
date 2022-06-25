package com.diego.poketinder.ui.view
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import com.diego.poketinder.databinding.ActivitySplashBinding
import com.diego.poketinder.util.SharedPreferenceUtil
import com.diego.poketinder.viewmodel.SplashViewModel
class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate){

    private lateinit var sharedPreferenceUtil:SharedPreferenceUtil
    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(this)
        }
        splashViewModel.getIsUnderMaintenance().observe(this) {
            if (it) {
                Toast.makeText(this, "Bajo Mantenimiento", Toast.LENGTH_SHORT).show()
            } else {
                showAnimation()
            }
        }
    }
        fun showAnimation() {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    //Evaluar si mostrar o no
                    val isIntroAvailable = sharedPreferenceUtil.getIntroShow()
                    if (!isIntroAvailable) {
                        startActivity(Intent(this, OnboardingActivity::class.java))
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    finish()
                },
                3000
            )
        }
}




