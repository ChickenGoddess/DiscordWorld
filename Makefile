all: DiscordWorld.jar

DiscordWorld.jar: classes manifest_cmd.mf
	jar cvfm DiscordWorld.jar manifest_cmd.mf res -C classes .

classes:
	mkdir classes
	javac -d classes src/*.java

manifest_cmd.mf:
	echo Main-Class: Game>>manifest_cmd.mf

clean:
	-rm DiscordWorld.jar
	-rm manifest_cmd.mf
	-rm -rf classes