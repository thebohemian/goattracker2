import com.github.thebohemian.libgoattracker.LibGoat2

println("loading shared library ...")

val lib = LibGoat2.load();

println("loaded library: ${lib}. Now starting GoatTracker2 through native invocation.")

// not providing any commandline arguments
lib.start_goattracker(0, emptyArray())
