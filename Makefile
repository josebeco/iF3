all: build run

build:
	javac -d bin iF3.java

run: build
	java -cp bin iF3

clear:
	rm -r bin

