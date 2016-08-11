package com.lifeistech.android.techdragon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private int power;
    private TextView powerTextView;
    private int hp;
    private ProgressBar hpBar;
    private ImageView dragonImageView;
    private TextView damageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerTextView = (TextView) findViewById(R.id.powerTextView);
        hpBar = (ProgressBar) findViewById(R.id.hpBar);
        hp = 100;
        dragonImageView = (ImageView) findViewById(R.id.dragonImageView);
        damageTextView = (TextView) findViewById(R.id.damageTextView);
    }


    public void powerUp(View v) {
        /*
         *
		 * 問題1 画面上のTextViewの数字が3ずつ増えていくボタンを作ろう。
		 * 問題2 数字が10以上30未満ならば文字の色が緑に、30以上50未満ならば青、
		 * 50以上ならば赤になるコードを、if文を使って問題1の関数の中に加えてみよう。
		 * 問題3 問題2の条件分岐コードをswitch文で書き換えてみよう。
		 * powerTextView.setTextColor(Color.BLUE);
        powerTextView.setTextColor(getResources().getColor(R.color.test_color));
		 * ヒント
		 * 以上 <= ; xは5以上: x >= 5
		 * より大きい < xは３より大きい: 3 < x
		 */
        power = power + 3;
        powerTextView.setText(String.valueOf(power));

        //かつ　&&
        //または ||
        if (power >= 10 && power < 30) {
            powerTextView.setTextColor(Color.GREEN);
        } else if (power >= 30 && power < 50) {
            powerTextView.setTextColor(Color.BLUE);
        } else if (power >= 50 && power < 100) {
            powerTextView.setTextColor(Color.RED);
        } else if (power >= 0 && power < 10) {
            powerTextView.setTextColor(Color.WHITE);
        }

    }


    public void attack(View v) {
        /*
         *
		 * 問題4 攻撃ボタンを作って、押すと相手にダメージを与えるボタンを作ってみよう。
		 * 問題5 「damageAnimation」メソッドを呼び出すメソッドを書き加えてみよう。
		 * 問題6 相手の体力が0以下になったときに「clearAnimation」メソッドを書き加えてみよう。
		 */
        Log.d("DragonHP", "brfore HP:" + hp);

        hp = hp - power;

        hpBar.setProgress(hp);
        Log.d("DragonHP", " after HP:" + hp);

        if (hp <= 0) {
//            dragonImageView.setVisibility(View.INVISIBLE);
            clearAnimation();
        }

        damageAnimation();

    }


    public void retry(View v) {
        /*
         *
		 * 問題7 はじめからやり直す「リトライボタン」を作って、最初の状態に戻してみよう。
		 * やることは下にかいてあるよ！
		 */
        // 相手のHP
        hp = 100;

        // 自分の攻撃力
        power = 0;
        powerTextView.setText(String.valueOf(power));

        // 相手モンスター復活
        dragonImageView.setVisibility(View.VISIBLE);

        // 攻撃力の文字の色を白に。(色が変わっている場合もあるので。)
        powerTextView.setTextColor(Color.WHITE);


    }


    public void info(View v) {
        Intent intent = new Intent(this, infoActivity.class);
        startActivity(intent);

    }


    // 攻撃アニメーション
    public void damageAnimation() {
        if (power > 0) {
            damageTextView.setText(String.valueOf(power));
        } else {
            damageTextView.setText("miss");
        }
        AttackAnimation attackAnim = new AttackAnimation(
                this.getApplicationContext(),
                dragonImageView, damageTextView);
        dragonImageView.startAnimation(attackAnim);
    }


    // 消滅アニメーション

    public void clearAnimation() {
        AlphaAnimation alphaAnim = new AlphaAnimation(1.0f, 0.0f);
        alphaAnim.setDuration(500);
        alphaAnim.setStartOffset(500);
        alphaAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation anim) {
            }

            @Override
            public void onAnimationRepeat(Animation anim) {
            }

            @Override
            public void onAnimationEnd(Animation anim) {
                dragonImageView.setVisibility(View.INVISIBLE);
            }
        });

        dragonImageView.startAnimation(alphaAnim);
    }


}
