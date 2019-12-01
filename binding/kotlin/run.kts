import com.github.thebohemian.libgoattracker.LibGoat2
import com.github.thebohemian.libgoattracker.ParameterTableReference

println("loading shared library ...")

val lib = LibGoat2.load();

lateinit var wv_l:ParameterTableReference
lateinit var wv_r:ParameterTableReference

println("loaded library: ${lib}. Now starting GoatTracker2 through native invocation.")

// not providing any commandline arguments
Thread {
	lib.start_goattracker(0, emptyArray())

}.apply {
	isDaemon = true
	start()
}

fun init() {
	//lib.load_binary_song()

	wv_l = lib.get_left_table(0)
	wv_r = lib.get_right_table(0)
}
