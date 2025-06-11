#!/bin/bash
rm -rf work
mkdir work
git init work
cd work
git config --local user.name "red"
git config --local user.email red@mail.ru
cp ../../data/commit0/* ./
git add .
git commit -m "r0"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout -b 'branch1'
cp ../../data/commit1/* ./
git add .
git commit -m "r1"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout -b "branch2"
cp ../../data/commit2/* ./
git add .
git commit -m "r2"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout -b "branch3"
cp ../../data/commit3/* ./
git add .
git status
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout main
cp ../../data/commit4/* ./
git add .
git commit -m "r4"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch1
cp ../../data/commit5/* ./
git add .
git commit -m "r5"
git checkout branch3
cp ../../data/commit6/* ./
git add .
git commit -m "r6"
git checkout -b "branch4"
cp ../../data/commit7/* ./
git add .
git commit -m "r7"
git checkout branch3
cp ../../data/commit8/* ./
git add .
git commit -m "r8"
git checkout branch1
cp ../../data/commit9/* ./
git add .
git commit -m "r9"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
cp ../../data/commit10/* ./
git add .
git commit -m "r10"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout -b "branch5"
cp ../../data/commit11/* ./
git add .
git commit -m "r11"
git checkout branch3
cp ../../data/commit12/* ./
git add .
git commit -m "r12"
git checkout branch4
cp ../../data/commit13/* ./
git add .
git commit -m "r13"
git checkout branch3
cp ../../data/commit14/* ./
git add .
git commit -m "r14"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
cp ../../data/commit15/* ./
git add .
git commit -m "r15"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch4
cp ../../data/commit16/* ./
git add .
git checkout branch5
cp ../../data/commit17/* ./
git add .
git commit -m "r17"
git checkout branch1
cp ../../data/commit18/* ./
git add .
git commit -m "r18"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
cp ../../data/commit19/* ./
git add .
git commit -m "r19"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch4
cp ../../data/commit20/* ./
git add .
git commit -m "r20"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout main
git merge branch4
git checkout --ours F.java
git checkout --theirs H.java
git checkout --theirs I.java
git add .
git commit -m "r22"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch5
cp ../../data/commit23/* ./
git add .
git commit -m "r23"
git checkout branch1
git merge branch5
git checkout --ours F.java
git checkout --theirs H.java
git checkout --ours I.java
git add .
git commit -m "r24"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
cp ../../data/commit25/* ./
git add .
git commit -m "r25"
cp ../../data/commit26/* ./
git add .
git commit -m "r26"
cp ../../data/commit27/* ./
git add .
git commit -m "r27"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch3
cp ../../data/commit28/* ./
git add .
git commit -m "r28"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
cp ../../data/commit29/* ./
git add .
git commit -m "r29"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch1
cp ../../data/commit30/* ./
git add .
git commit -m "r30"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout branch2
git merge branch1
git checkout --theirs F.java
git checkout --ours H.java
git checkout --ours I.java
git add .
git commit -m "r31"
git config --local user.name "blue"
git config --local user.email blue@mail.ru
git checkout branch3
git merge branch2
git checkout --theirs F.java
git checkout --ours H.java
git checkout --theirs I.java
git add .
git commit -m "r32"
git config --local user.name "red"
git config --local user.email red@mail.ru
git checkout main
git merge branch3
git checkout --ours H.java
git checkout --theirs I.java
git add .
git commit -m "r33"
git log --graph --all --decorate --format="%C(auto)%h%C(reset) %C(bold cyan)%s%C(reset) %C(bold magenta)%an%C(reset) %C(auto)%d%C(reset)" 



