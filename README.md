# Korean Delight (K-FOOD)

[**한국어 설명은 아래를 참고해 주세요.**](#한국어)

An immersive Minecraft NeoForge mod that introduces traditional Korean cuisine, crops, and custom culinary mechanics, designed as a companion addon to **Farmer's Delight**.

---

## 🌟 Features

### 🍲 Traditional Korean Dishes
Experience a wide variety of Korean dishes, each crafted with specialized ingredients:
* **Kimchi (김치)**: The signature fermented vegetable dish. Gives fire resistance and speed!
* **Bibimbap (비빔밥)**, **Jeyuk-bokkeum (제육볶음)**, **Tteokbokki (떡볶이)**, **Janchi-guksu (잔치국수)**, **Buldak Noodle (불닭볶음면)**, **Yangnyeom Chicken (양념치킨)**, **Miyeok-guk (미역국)**, **Bulgogi (불고기)**, **Sikhye (식혜)**, and **Doenjang Jjigae (된장찌개)**.

### 🚜 Crops & Ingredients
Grow and harvest essential Korean agricultural crops:
* **Crops**: Chili Pepper, Garlic, Sesame, Green Onion, and Soybean.
* **Ingredients**: Chili Powder, Salt, Soy Sauce, Sesame Oil, Sesame Seeds, Gochujang, Somen, Deep-fried Noodles, Tofu, Rice Cake, Fish Cake, and Meju.

### ⚙️ Custom Machinery: The Presser (압착기)
An interactive block used to press and process raw materials:
* **Tofu**: 2 Soybean + 1 Salt -> Tofu
* **Sesame Oil**: 1 Sesame Seeds -> Sesame Oil
* **Rice Cake**: 2 Rice -> 2 Rice Cake
* **Fish Cake**: 1 Fish + 1 Wheat + 1 Salt -> 2 Fish Cake
* **Meju**: 3 Soybean -> 1 Meju Block
* **JEI Compatible**: Complete recipes automatically map to Just Enough Items (JEI) click areas inside the GUI.

---

## 🛠️ Requirements & Dependencies
* **Minecraft**: `1.21.1`
* **Mod Loader**: **NeoForge** `[21.1.0,)`
* **Required Mod**: **Farmer's Delight (NeoForge)** `[1.3.2,)`

---

## 📦 How to Build
This repository contains a clean NeoForge development workspace. To compile the mod into a JAR file, run:
```bash
# Windows
gradlew.bat build

# macOS / Linux
./gradlew build
```
The compiled mod JAR will be generated under `build/libs/`.

---

<div id="한국어"></div>

# 한국어 (Korean)

**Korean Delight (K-FOOD)** 모드는 **Farmer's Delight** 모드의 애드온으로 설계된 마인크래프트 네오포지(NeoForge) 모드로, 한국 전통 음식과 작물, 그리고 압착기 등의 고유 제작 기계를 추가합니다.

---

## 🌟 주요 기능

### 🍲 전통 한식 요리
특별한 재료들을 모아 다양한 한국 음식을 요리할 수 있습니다:
* **김치**: 한국의 대표적인 발효 채소 요리. 화염 저항과 신속 버프를 제공합니다.
* **비빔밥**, **제육볶음**, **떡볶이**, **잔치국수**, **불닭볶음면**, **양념치킨**, **미역국**, **불고기**, **식혜**, **된장찌개** 등.

### 🚜 새로운 작물과 식재료
농사를 통해 한식의 필수 작물들을 직접 재배할 수 있습니다:
* **작물**: 고추, 마늘, 참깨, 대파, 콩.
* **식재료**: 고춧가루, 소금, 간장, 참기름, 참깨, 고추장, 소면, 유탕면, 두부, 떡, 어묵, 메주.

### ⚙️ 고유 기계: 압착기 (Presser)
재료를 압착하여 다양한 2차 가공품을 만들어냅니다:
* **두부**: 콩 2개 + 소금 1개 -> 두부
* **참기름**: 참깨 1개 -> 참기름
* **떡**: 쌀 2개 -> 떡 2개
* **어묵**: 물고기 1개 + 밀 1개 + 소금 1개 -> 어묵 2개
* **메주**: 콩 3개 -> 메주 블록 1개
* **JEI 호환**: 압착기 GUI 내의 제작법 보기(초록색 책 버튼 및 화살표) 클릭 시 JEI 화면과 연동됩니다.

---

## 🛠️ 실행 요구 사항
* **마인크래프트 버전**: `1.21.1`
* **모드 로더**: **NeoForge** `[21.1.0,)`
* **필수 요구 모드**: **Farmer's Delight (NeoForge)** `[1.3.2,)`

---

## 📦 직접 빌드하는 방법
이 저장소는 깨끗한 네오포지 개발 환경을 포함하고 있습니다. 소스코드를 빌드하여 모드 JAR 파일을 생성하려면 터미널에서 다음 명령어를 실행하세요:
```bash
# 윈도우 (Windows)
gradlew.bat build

# 맥 / 리눅스 (macOS / Linux)
./gradlew build
```
빌드가 성공하면 `build/libs/` 폴더 내에 모드 `.jar` 파일이 생성됩니다.
