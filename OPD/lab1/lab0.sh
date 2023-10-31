#!bin/bash/
mkdir ~/opd/lab0
mkdir ~/opd/lab0/deino4
mkdir ~/opd/lab0/deino4/starly
mkdir ~/opd/lab0/deino4/bibarel
touch ~/opd/lab0/deino4/espeon
mkdir ~/opd/lab0/drifloon0
mkdir ~/opd/lab0/drifloon0/slowbro
touch ~/opd/lab0/drifloon0/lairon
mkdir ~/opd/lab0/drifloon0/persian
touch ~/opd/lab0/drifloon0/clefairy
mkdir ~/opd/lab0/drifloon0/sunkern
mkdir ~/opd/lab0/klang8
mkdir ~/opd/lab0/klang8/patrat
mkdir ~/opd/lab0/klang8/ledyba
mkdir ~/opd/lab0/klang8/grotle
mkdir ~/opd/lab0/klang8/scrafty
touch ~/opd/lab0/leavanny5
touch ~/opd/lab0/tyranitar7
touch ~/opd/lab0/watchog5
echo satk=13 sdef=10 spd=11 >> ~/opd/lab0/deino4/espeon
echo Живет Cave >> ~/opd/lab0/drifloon0/lairon
echo Mountain >> ~/opd/lab0/drifloon0/lairon
echo Способности Encore Sing Doubleslap Defense Curl >> ~/opd/lab0/drifloon0/clefairy
echo Follow Me Bestrow Wake-Up Slap Minimize Sroted Power Metronome Cosmic >> ~/opd/lab0/drifloon0/clefairy
echo POwer Lucky Chant Body Slam Moonlight Light Screen Gravity Meteor Mash >> ~/opd/lab0/drifloon0/clefairy
echo Healing Wish After You >> ~/opd/lab0/drifloon0/clefairy
echo Тип диеты Herbivore >> ~/opd/lab0/leavanny5
echo Тип >> ~/opd/lab0/tyranitar7
echo диеты Carnviore Terravore >> ~/opd/lab0/tyranitar7
echo Возможности Overland=8 Surface=6 >> ~/opd/lab0/watchog5
echo Jump=4 POwer=3 Intellegence=4 Tracker=0 >> ~/opd/lab0/watchog5

chmod u=rwx,g=wx,o=rw ~/opd/lab0/deino4
chmod u=rwx,g=rwx,o=rwx ~/opd/lab0/deino4/starly
chmod 337 ~/opd/lab0/deino4/bibarel
chmod 440 ~/opd/lab0/deino4/espeon
chmod 770 ~/opd/lab0/drifloon0
chmod u=rx,g=x,o=r ~/opd/lab0/drifloon0/slowbro
chmod 044 ~/opd/lab0/drifloon0/lairon
chmod 733 ~/opd/lab0/drifloon0/persian
chmod 440 ~/opd/lab0/drifloon0/clefairy
chmod 771 ~/opd/lab0/drifloon0/sunkern
chmod 555 ~/opd/lab0/klang8
chmod 577 ~/opd/lab0/klang8/patrat
chmod 771 ~/opd/lab0/klang8/ledyba
chmod 570 ~/opd/lab0/klang8/grotle
chmod 736 ~/opd/lab0/klang8/scrafty
chmod 404 ~/opd/lab0/leavanny5
chmod 444 ~/opd/lab0/tyranitar7
chmod 444 ~/opd/lab0/watchog5

ln -s ~/opd/lab0/deino4 ~/opd/lab0/Copy_54 
chmod 744 ~/opd/lab0/drifloon0/lairon
cat  ~/opd/lab0/drifloon0/clefairy ~/opd/lab0/drifloon0/lairon> ~/opd/lab0/leavanny5_80
chmod 044 ~/opd/lab0/drifloon0/lairon
ln -s ~/opd/lab0/leavanny5 ~/opd/lab0/drifloon0/clefairyleavanny 
cp -r ~/opd/lab0/klang8 ~/opd/lab0/deino4/starly
cat ~/opd/lab0/tyranitar7 > ~/opd/lab0/deino4/espeontyranitar
ln ~/opd/lab0/tyranitar7 ~/opd/lab0/deino4/espeontyranitar
cp ~/opd/lab0/leavanny5 ~/opd/lab0/drifloon0/persian

chmod u+rx ~/opd/lab0/drifloon0/lairon
chmod u+rx ~/opd/lab0/deino4/bibarel
wc -m ~/opd/lab0/watchog5 >> ~/opd/lab0/watchog5  cat 2>/dev/stdout
ls -rl ~/opd/lab0 | tail -n 3 | sort -k5  2>/tmp/my_error.txt
grep -Ri "[^ght]" ~/opd/lab0/klang8/*  2>/tmp/my_error.txt
grep -Rn "^l" ~/opd/lab0 | sort  2>/tmp/my_error.txt
ls -ra ~/opd/lab0 | head -n 3 | sort  2>/dev/null
grep -hrn "n$" ~/opd/lab0 | sort  2>/tmp/my_error.txt

chmod -R u+rwx ~/opd/lab0
rm ~/opd/lab0/tyranitar7
rm ~/opd/lab0/drifloon0/clefairy
rm -r ~/opd/lab0/Copy_*
rm ~/opd/lab0/deino4/espeontyranit*
rmdir ~/opd/lab0/klang8/scrafty
rm -R ~/opd/lab0/klang8
