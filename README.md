# VerseMind 📚🔍

![VerseMind Logo](https://example.com/versemind-logo.png)

VerseMind is an innovative Android application that harnesses the power of AI to provide insightful answers to your Bible-related questions. It's your personal digital companion for deeper biblical understanding.

![App Screenshot](https://example.com/versemind-screenshot.png)

## ✨ Features

- 🤖 **AI-Powered Bible Q&A**: Get detailed, AI-generated responses to your biblical queries
- 📝 **Markdown Rendering**: Beautifully formatted answers for enhanced readability
- 🎨 **Intuitive UI**: Clean, user-friendly interface for seamless navigation
- ⚡ **Asynchronous Processing**: Fast, efficient background processing of API requests
- 🛡️ **Robust Error Handling**: User-friendly error messages for a smooth experience
- 📜 **Scrollable Content**: Easily read through long answers with our scrollable interface
- 🌙 **Dark Theme**: Easy on the eyes, perfect for night-time study sessions

## 🛠️ Technical Specifications

- **Language**: Kotlin
- **Minimum SDK**: 25 (Android 7.1+)
- **Target SDK**: 34 (Android 14)
- **Architecture**: Single-activity with ConstraintLayout
- **Networking**: OkHttp
- **Markdown**: Markwon library
- **Async Operations**: Kotlin Coroutines

## 🚀 Setup and Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/spragginsdesigns/VerseMind.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device (API 25+).

## 📱 Usage

1. Launch VerseMind on your Android device.
2. Type your Bible-related question in the text field.
3. Tap "Submit" to send your question.
4. Wait for the AI to generate your answer.
5. Scroll through the response if needed.

## 🔗 API Integration

VerseMind connects to our custom API for processing questions:

```
https://bible-ai-explorer.vercel.app/api/ask-question
```

⚠️ Ensure proper API permissions and keys are set up for production use.

## 🎨 Customization

- **Colors**: `res/values/colors.xml`
- **Strings**: `res/values/strings.xml`
- **Layouts**: `res/layout/activity_main.xml`
- **Styles**: `res/values/themes.xml`

## 🤝 Contributing

We welcome contributions to VerseMind! Here's how:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` file for more information.

## 🙏 Acknowledgements

- [OkHttp](https://square.github.io/okhttp/)
- [Markwon](https://noties.io/Markwon/)
- [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

---

Made with ❤️ and ☕ by Austin Spraggins
