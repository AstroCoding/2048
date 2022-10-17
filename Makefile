#Path separator is different on Windows versus Unix based OSes
ifeq ($(OS),Windows_NT)
	SEP=;
else
	SEP=:
endif

JFLAGS = -g
JCLASS = -cp "src$(SEP).$(SEP)../junit-4.5.jar"
JC = javac
JVM = java

EXPT = expt
TEST = TestAll

.PHONY: test clean

expt:
	$(JC) $(JCLASS) $(JFLAGS) src/$(EXPT).java
	$(JVM) src/$(EXPT)

test:
	find . -name '*.class' -exec rm -f {} \;
	$(JC) $(JCLASS) $(JFLAGS) src/$(TEST).java
	$(JVM) $(JCLASS) org.junit.runner.JUnitCore src.$(TEST)

doc:
	doxygen doxConfig
	cd ./latex && $(MAKE)
	cd ../spec && $(MAKE)

clean:
	rm -rf html
	rm -rf latex
	cd src
	rm **/*.class

play:
	$(JC) $(JCLASS) $(JFLAGS) src/GUIGraphics.java
	$(JVM) src/GUIGraphics