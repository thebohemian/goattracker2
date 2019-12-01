ARTIFACT=goat2
VERSION=0.0.1
JARLIB=lib$(ARTIFACT)-$(VERSION).jar
CLASSPATH=/usr/share/java/jna.jar
COMPILE=kotlinc
FLAGS=-cp $(CLASSPATH)

RUNTIME=kotlinc-jvm
JNA_LIBRARY_PATH=-Djna.library.path=../../lib
RUNTIME_FLAGS=-cp $(CLASSPATH):$(JARLIB) $(JNA_LIBRARY_PATH)

SOURCES=$(shell find src -name *.kt)


$(JARLIB): $(SOURCES)
	$(COMPILE) $(FLAGS) -d $@ $<

run: $(JARLIB)
	echo $(SOURCES)
	$(RUNTIME) $(RUNTIME_FLAGS) -script run.kts

runinteractive: $(JARLIB)
	$(RUNTIME) $(RUNTIME_FLAGS)

clean:
	$(RM) $(JARLIB)
