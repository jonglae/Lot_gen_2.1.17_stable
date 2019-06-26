package gotopark.buster.lottogen

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import gotopark.buster.lottogen.Module.MonyCalc
import gotopark.buster.lottogen.Module.numtoimg2
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@SuppressLint("StaticFieldLeak")
// 메인 엑티비티 텍스트 표시 접근
class Jsoup_Lotto(context: Activity) : AsyncTask<Void, Void?, Void?>() {
    // 메인 엑티비티 텍스트 표시 접근
    companion object {

        // 메인 엑티비티 텍스트 표시 접근
        lateinit var activity: MainActivity
        lateinit var SUM_lotto_num: String
        lateinit var LotDate: String
    }

    init {
        activity=context as MainActivity
    }

    private lateinit var F10: Elements
    private lateinit var F11: Elements
    private lateinit var F12: Elements
    private lateinit var F13: Elements
    private lateinit var F14: Elements
    private lateinit var F15: Elements
    private var tiTle: String?=null

    public override fun doInBackground(vararg params: Void): Void? {

        //https 설정 ssl hanshare fail 적용 위한 code -->start
        val trustAllCerts=arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate?> {
                return arrayOfNulls(0)
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {
            }
        })

        var sc: SSLContext?=null
        try {
            sc=SSLContext.getInstance("TLS")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        try {
            assert(sc != null)
            sc!!.init(null, trustAllCerts, SecureRandom())
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }

        HttpsURLConnection.setDefaultSSLSocketFactory(sc!!.socketFactory)
        //https 설정 ssl hanshare fail 적용 위한 code --> End

        val url=MainActivity.CONTEXT.getString(R.string.JsoupOne)

        try {

            val document=Jsoup.connect(url)
                    .timeout(0)
                    .get()


            // Get the html document title
            tiTle=document.title()
            F10=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q1))
            F11=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q2))
            F12=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q3))
            F13=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q4))
            F14=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q5))
            F15=document.select(MainActivity.CONTEXT.getString(R.string.jsoup_q6))

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    @SuppressLint("SetTextI18n")
    override fun onPostExecute(result: Void?) {


        var lotto_num=arrayOfNulls<String>(7)
        val LotCount: String
        var LotWin: String
        var prize_data: String
        val SUM_lotto_num1: String



        if (tiTle != null) {

            var KoLotto=""
            for (e in F12) {
                val alt=e.attr("alt")

                KoLotto+=", $alt"
            }
            KoLotto=KoLotto.replace(" ".toRegex(), "")

            KoLotto=KoLotto.substring(1)
            lotto_num=KoLotto.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            SUM_lotto_num1=lotto_num[0] + ", " +
                    lotto_num[1] + ", " +
                    lotto_num[2] + ", " +
                    lotto_num[3] + ", " +
                    lotto_num[4] + ", " +
                    lotto_num[5]


            SUM_lotto_num=SUM_lotto_num1 + "\n보너스 번호 :" + lotto_num[6]


            LotCount=F10.toString().replace("\\<.*?>".toRegex(), "")
            LotDate=F11.toString().replace("\\<.*?>".toRegex(), "")

            // 로또 당첨 등수
            LotWin=F13.toString().replace("\\<.*?>".toRegex(), "")
            LotWin=LotWin.replace("  ".toRegex(), "")
            LotWin=LotWin.replace("\n".toRegex(), "")
            LotWin=LotWin.replace("".toRegex(), "")
            LotWin=LotWin.replace("  ".toRegex(), "\n")
            LotWin=LotWin.replace("당첨정보 상세보기".toRegex(), "")
            LotWin=LotWin.replace("₩".toRegex(), " = (₩)")


            Log.e("================>", F14.toString())
            // 다음회차 정보
            var Linfo=F14.toString().replace("\\<.*?>".toRegex(), "")
            Linfo=Linfo.replace("\n".toRegex(), "")
            Log.e("1Linfo================>", Linfo)

            Linfo=Linfo.replace("   ".toRegex(), "\n")
            Log.e("2Linfo===============>", Linfo)

            Linfo=Linfo.replace("  ".toRegex(), "")
            Log.e("3Linfo===============>", Linfo)
            prize_data=F15.toString().replace("\\<.*?>".toRegex(), "")
            prize_data=prize_data.replace("₩".toRegex(), "")
            prize_data=prize_data.replace(",".toRegex(), "")

            //                prize_data = String.valueOf(F15);


            val Cpriz_data=MonyCalc.convertHangul(prize_data)
            Log.e("3Linfo===============>", Cpriz_data)

            val res=IntArray(7)

            val dball1: Int
            val dball2: Int
            val dball3: Int
            val dball4: Int
            val dball5: Int
            val dball6: Int
            val dball8: Int

            val NumtoI2=numtoimg2()


            res[0]=Integer.parseInt(lotto_num[0].toString())
            res[1]=Integer.parseInt(lotto_num[1].toString())
            res[2]=Integer.parseInt(lotto_num[2].toString())
            res[3]=Integer.parseInt(lotto_num[3].toString())
            res[4]=Integer.parseInt(lotto_num[4].toString())
            res[5]=Integer.parseInt(lotto_num[5].toString())
            res[6]=Integer.parseInt(lotto_num[6].toString())

            Model.setWeeknum(lotto_num)

            dball1=NumtoI2.Numimg2(res[0])
            dball2=NumtoI2.Numimg2(res[1])
            dball3=NumtoI2.Numimg2(res[2])
            dball4=NumtoI2.Numimg2(res[3])
            dball5=NumtoI2.Numimg2(res[4])
            dball6=NumtoI2.Numimg2(res[5])
            dball8=NumtoI2.Numimg2(res[6])


            activity.Rtilte.text=LotCount + "추첨일:" + LotDate
            activity.Resultwin1.text=LotWin
            activity.Resultwin2.text=Linfo
            activity.Resultwin3.text=Cpriz_data


            // 당첨 번호 표시
            activity.Rtext1.text=lotto_num[0].toString()
            activity.Rtext2.text=lotto_num[1].toString()
            activity.Rtext3.text=lotto_num[2].toString()
            activity.Rtext4.text=lotto_num[3].toString()
            activity.Rtext5.text=lotto_num[4].toString()
            activity.Rtext6.text=lotto_num[5].toString()
            activity.Rtext7.text="+"
            activity.Rtext8.text=lotto_num[6].toString()


            activity.Rtext1.setBackgroundResource(dball1)
            activity.Rtext2.setBackgroundResource(dball2)
            activity.Rtext3.setBackgroundResource(dball3)
            activity.Rtext4.setBackgroundResource(dball4)
            activity.Rtext5.setBackgroundResource(dball5)
            activity.Rtext6.setBackgroundResource(dball6)
            activity.Rtext8.setBackgroundResource(dball8)


            // 당첨된 번호 랜덤 번호 생성 부분에 표시
            activity.Balltxt1.text=lotto_num[0].toString()
            activity.Balltxt2.text=lotto_num[1].toString()
            activity.Balltxt3.text=lotto_num[2].toString()
            activity.Balltxt4.text=lotto_num[3].toString()
            activity.Balltxt5.text=lotto_num[4].toString()
            activity.Balltxt6.text=lotto_num[5].toString()


        }
    }
}