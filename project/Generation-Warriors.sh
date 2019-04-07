#!bin/sh
echo "Lancement de la génération"
echo "Paramétrage ..."
read -p 'Entrez le Nombre de Warriors (50 obligatoires) :' war
read -p 'Entrez le Nombre de Warriors à garder (sans mutations avec max Nombre de Warriors-1) : ' warsansmute
read -p 'Entrez le Nombre de secondes max pour un combat (Attention, cela influence beaucoup le temps de génération) :' secondes
read -p 'Entrez le Nombre de combats par Warriors (Attention, cela influence beaucoup le temps de génération) :' nbwar
echo "Lancement ..."
[ -d build ] || mkdir build
javac -d build src/*/*.java src/*.java src/genetics/*/*.java src/genetics/*.java
sleep 2
java -cp build/ genetics.Generation $war $warsansmute $secondes $nbwar
