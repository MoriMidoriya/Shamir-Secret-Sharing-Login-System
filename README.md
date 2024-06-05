# 🔐 Shamir's Secret Sharing Login System

A secure login system leveraging Shamir's Secret Sharing scheme to split and reconstruct secrets (passwords) in a distributed manner.

## 🌟 Features

- **Secure Secret Sharing:** Implements Shamir's Secret Sharing to split a secret into multiple shares.
- **Polynomial Generation:** Creates polynomials to generate shares based on a secret and threshold.
- **Secret Reconstruction:** Uses Lagrange Interpolation to reconstruct the secret from selected shares.
- **Client-Server Architecture:** Communicates securely between client and server using sockets.
- **User-Friendly Interface:** Provides a GUI for users to enter secrets, thresholds, and share selections.

## 🛠️ Built With

- **Python 3.x**
- **Socket Library**: For client-server communication.
- **Pickle Library**: For serializing and deserializing data.
- **Random Library**: For generating random coefficients.
- **Tkinter Library**: For the graphical user interface.
- **ttkthemes Library**: For themed styles in the GUI.
- **Pillow Library**: For handling images in the GUI.

--- A Java version is also available in the repository. It has both the client and server side code without the GUI. ---

## 📦 Installation

1. **Clone the Repository:**
    ```sh
    git clone https://github.com/MoriMidoriya/Shamir-Secret-Sharing-Login-System.git
    cd shamir-secret-sharing-login
    ```

2. **Install Required Libraries:**
    ```sh
    pip install ttkthemes pillow sympy
    ```

## 🚀 Usage

### Server Side

1. **Run the Server:**
    ```sh
    python server.py
    ```
    This starts the server, which listens for incoming connections on `127.0.0.1:8080`. (You can change the host and port accordingly)

### Client Side

1. **Run the Client Application:**
    ```sh
    python client.py
    ```
    This opens a GUI for the user to interact with the system.

2. **Enter Details in the GUI:**
    - **Enter Secret:** The secret that you want to protect.
    - **Enter Threshold:** The minimum number of shares required to reconstruct the secret.
    - **Enter Total Shares:** The total number of shares to generate.

3. **Select Shares:**
    - Choose the shares to use for reconstructing the secret.
    - Click "Login" to attempt reconstruction and verify the secret.

## 📋 File Descriptions

### `client.py`

This file contains the client-side code that provides a GUI for user interaction and communicates with the server to verify the reconstructed secret.

- **Key Functions:**
    - `CreatePolynomial()`: Generates a polynomial based on given coefficients.
    - `CreateShares()`: Creates shares using Shamir's Secret Sharing.
    - `lagrange_interpolation()`: Reconstructs the secret using Lagrange interpolation.
    - `connect_to_server()`: Establishes a connection to the server.
    - `send_data_to_server()`: Sends serialized data to the server.

### `server.py`

This file contains the server-side code that handles incoming client requests, performs secret sharing, and reconstructs the secret.

- **Key Functions:**
    - `CreatePolynomial()`: Generates a polynomial based on given coefficients.
    - `CreateShares()`: Creates shares using Shamir's Secret Sharing.
    - `lagrange_interpolation()`: Reconstructs the secret using Lagrange interpolation.
    - `handle_client()`: Processes client requests and sends back the result.
    - `run_server()`: Runs the server to listen for incoming connections.

## 🖼️ Screenshots

*Add screenshots of your GUI here to give users a visual overview of your application.*

## 📞 Contact

*For any queries or issues, feel free to reach out or open an issue in the repository.*

---

Thank you for using the Shamir's Secret Sharing Login System! 🎉
