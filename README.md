# WeatherApp
### WeatherApp は、天気情報を取得するための Android アプリケーションです。
## 概要
このプロジェクトは、OpenWeather WeatherAPIとGoogle Fontsを使用しています。WeatherAPIを使用するには、APIキーの取得と設定が必要です。

## APIキーの設定手順
APIキーの設定には以下の手順を追ってください。初めての方でも簡単に設定できます。

### OpenWeatherAPI

- [OpenWeatherの公式サイト](https://openweathermap.org/api) にアクセスし、アカウントを作成します。
- APIキーを取得します。
- プロジェクトのルートディレクトリにある `local.properties` ファイルを開きます。
- `api_key="YOUR_OPENWEATHER_API_KEY"` の形式で、取得したOpenWeatherAPIのAPIキーを設定します。
```
#WeatherAPI
api_key="YOUR_OPENWEATHER_API_KEY"
```
これらの手順に従ってAPIキーを設定することで、プロジェクトが正しく動作します。

## 使用しているAPI

### [OpenWeatherのWeatherAPI](https://openweathermap.org/api)
OpenWeather WeatherAPIは、天気情報を提供するAPIです。

### [Google Fonts](https://fonts.google.com/icons)
Google Fontsは、様々なフォントを提供するAPIです。詳細は公式ドキュメンテーションをご覧ください。

## ライセンス
このプロジェクトはMITライセンスの下で公開されています。詳細はLICENSEをご覧ください。

## 開発環境

- コンパイルSDKバージョン: 34
- 最小SDKバージョン: 30
- ターゲットSDKバージョン: 34
- Android Studio Hedgehog | 2023.1.1

### その他

- Javaソースコンパイルバージョン: 1.8
- Kotlin JVM ターゲットバージョン: 1.8
