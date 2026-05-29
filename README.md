# AndroidBridge — Step-by-Step Setup Guide

## What this is
An Android app that lets you remotely control your phone over the internet from your PC.
Features: screen view, tap/swipe control, camera access, microphone, app manager, file browser, shell.

---

## PART 1 — Upload to GitHub (builds the APK for free)

### Step 1 — Create a free GitHub account
1. Go to https://github.com
2. Click "Sign up" and create a free account
3. Verify your email

### Step 2 — Create a new repository
1. Click the **+** icon (top right) → "New repository"
2. Name it: `androidbridge`
3. Make sure it's set to **Public**
4. Click **"Create repository"**

### Step 3 — Upload all the files
1. On your new empty repository page, click **"uploading an existing file"**
2. You need to upload the files maintaining their folder structure.
   The easiest way is to use GitHub Desktop (free app):
   - Download from: https://desktop.github.com
   - Install and sign in with your GitHub account
   - Click "Clone a repository" → choose your `androidbridge` repo
   - Copy all the files from this ZIP into the cloned folder
   - In GitHub Desktop, click "Commit to main" → "Push origin"

### Step 4 — Watch it build
1. Go to your repository on github.com
2. Click the **"Actions"** tab at the top
3. You'll see "Build AndroidBridge APK" running (yellow spinner)
4. Wait 3–5 minutes for it to complete (green checkmark)

### Step 5 — Download your APK
1. Click on the completed build
2. Scroll down to **"Artifacts"**
3. Click **"AndroidBridge-debug"** to download a ZIP
4. Extract the ZIP — inside is `app-debug.apk`

---

## PART 2 — Install the APK on your phone

### Step 1 — Allow unknown sources
1. On your Android phone, go to **Settings**
2. Search for "Install unknown apps" or go to:
   Settings → Apps → Special app access → Install unknown apps
3. Find your file manager or browser and enable "Allow from this source"

### Step 2 — Transfer and install the APK
1. Copy `app-debug.apk` to your phone (via USB cable, WhatsApp, email, Google Drive — any way)
2. Open the file on your phone
3. Tap **"Install"**
4. Tap **"Open"** when done

### Step 3 — Grant permissions
When the app opens, it will ask for:
- **Camera** → tap Allow
- **Microphone** → tap Allow  
- **Storage** → tap Allow
- **Battery optimization** → tap "Allow" (keeps app running in background)

---

## PART 3 — Set up the PC server

### Step 1 — Install Node.js
1. Go to https://nodejs.org
2. Download the **LTS** version (big green button)
3. Install it (just click Next → Next → Finish)

### Step 2 — Install the server
1. Extract the `server` folder from this ZIP to your Desktop
2. Open a terminal / command prompt:
   - Windows: press Win+R, type `cmd`, press Enter
   - Mac: open Terminal from Applications
3. Type these commands:
   ```
   cd Desktop\server
   npm install
   ```

### Step 3 — Run the server
```
node server.js
```
You'll see a box printed with your **auth token** — copy it, you'll need it.

### Step 4 — Start a tunnel (so your phone can reach your PC over the internet)

**Option A — Ngrok (recommended):**
1. Go to https://ngrok.com → Sign up free
2. Download ngrok for your OS
3. Run: `ngrok config add-authtoken YOUR_TOKEN_FROM_NGROK_WEBSITE`
4. Run: `ngrok http 3737`
5. Copy the `https://abc123.ngrok-free.app` URL shown

**Option B — Cloudflare (no account needed):**
1. Go to https://developers.cloudflare.com/cloudflare-one/connections/connect-networks/downloads/
2. Download `cloudflared` for your OS
3. Run: `cloudflared tunnel --url http://localhost:3737`
4. Copy the `https://xxx.trycloudflare.com` URL shown

---

## PART 4 — Connect phone to PC

1. Open the **AndroidBridge** app on your phone
2. Paste the tunnel URL (from ngrok or cloudflare)
3. Paste the auth token (from the server console)
4. Tap **Connect**

You're connected! 🎉

---

## Tips

- **The app auto-starts** when you reboot your phone
- **The server must be running** on your PC for the connection to work
- **Ngrok free URLs change** every time you restart ngrok — update the URL in the app when that happens
- For a **permanent URL**, use ngrok paid plan ($8/month) or set up a named Cloudflare tunnel (free)
- The app shows a persistent notification — this is normal and keeps it running

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| "Connection failed" | Make sure server.js is running AND ngrok/cloudflare is running |
| "Wrong auth token" | Copy the token exactly from the server console (no spaces) |
| App keeps stopping | Go to phone Settings → Battery → AndroidBridge → No restrictions |
| Build fails on GitHub | Check the Actions tab for the error message and share it with me |
