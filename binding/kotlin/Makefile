JARLIB=goat2.jar
CLASSPATH=/usr/share/java/jna.jar
COMPILE=kotlinc
FLAGS=-cp $(CLASSPATH)

RUNTIME=kotlinc-jvm
JNA_LIBRARY_PATH=-Djna.library.path=../../lib
RUNTIME_FLAGS=-cp $(CLASSPATH):$(JARLIB) $(JNA_LIBRARY_PATH) -script run.kts

$(JARLIB): src/LibGoat2.kt
	$(COMPILE) $(FLAGS) -d $@ $<

run: $(JARLIB)
	$(RUNTIME) $(RUNTIME_FLAGS)

clean:
	$(RM) $(JARLIB)
