package id.jammasjid.digital

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.WindowManager
import fi.iki.elonen.NanoHTTPD
import org.json.JSONObject
import java.io.File

class MainActivity : Activity() {
 private lateinit var server: LocalServer
 override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)
  window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
  window.decorView.systemUiVisibility = 5894 or 1024 or 512
  server = LocalServer(this); server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
  val w=WebView(this); w.settings.javaScriptEnabled=true; w.settings.domStorageEnabled=true; w.webViewClient=WebViewClient(); setContentView(w); w.loadUrl("http://127.0.0.1:8080/")
 }
 override fun onDestroy(){ try{server.stop()}catch(_:Exception){}; super.onDestroy() }
}
class LocalServer(private val activity: Activity): NanoHTTPD(8080) {
 private val configFile = File(activity.filesDir,"settings.json")
 private val defaults = JSONObject("""{"mosque":"Masjid Baitul Ghofar","location":"Surabaya","timezone":"Asia/Jakarta","text":"Selamat datang di masjid kami. Mohon luruskan dan rapatkan shaf.","times":{"Subuh":"04:20","Syuruq":"05:40","Dzuhur":"11:40","Ashar":"15:00","Maghrib":"17:35","Isya":"18:45"},"iqamah":{"Subuh":10,"Dzuhur":10,"Ashar":10,"Maghrib":5,"Isya":10},"offset":{"Subuh":0,"Dzuhur":0,"Ashar":0,"Maghrib":0,"Isya":0}}""")
 private fun readConfig(): JSONObject = try { JSONObject(configFile.readText()) } catch(_:Exception){ defaults }
 override fun serve(session: IHTTPSession): Response {
  if(session.method==Method.POST && session.uri=="/api/settings") { val body=HashMap<String,String>(); session.parseBody(body); val data=JSONObject(body["postData"]?:"{}"); configFile.writeText(data.toString(2)); return newFixedLengthResponse(Response.Status.OK,"application/json", "{\"ok\":true}") }
  if(session.uri=="/api/settings") return newFixedLengthResponse(Response.Status.OK,"application/json",readConfig().toString())
  val path=if(session.uri=="/"||session.uri=="/admin") "index.html" else session.uri.trimStart('/')
  return try { val stream=activity.assets.open(path); newChunkedResponse(Response.Status.OK, mime(path),stream) } catch(_:Exception){newFixedLengthResponse(Response.Status.NOT_FOUND,"text/plain","File tidak ditemukan")}
 }
 private fun mime(p:String)=when { p.endsWith(".html")->"text/html; charset=utf-8"; p.endsWith(".css")->"text/css"; p.endsWith(".js")->"application/javascript"; else->"text/plain" }
}
