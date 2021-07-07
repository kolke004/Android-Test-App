////////////////
//// README ////
////////////////

PLEASE READ THOROUGHLY PRIOR TO APP LAUNCH

Reach out to Joe Kolker via Slack with questions if needed

////////////////////////////
//// SETUP INSTRUCTIONS ////
////////////////////////////

!!Set your URL Scheme in Android project!!

Under Android > App > Manifests > AndroidManifest.xml, look for the two warning comments mentioning Scheme ID
Grab the URL Scheme ID from your app.pendo.io subscription settings
	Subcription Settings > {Name of your Android App} App Details > Install Settings > Scroll to Step 2
	Look for something formatted like so: scheme="pendo-{random string}"
	Copy everything within the double quotes
Go back Android Studio
	In the AndroidManifest.xml file, look for where it says "INSERT URL SCHEME HERE"
	Paste the full URL Scheme ID where it specifies on line 32 (within the double quotes)

!!Insert your Pendo API Key!!

Under Android > App > Java > LoginActivity, look for the PENDO comment mention API key(s)
Grab the API key from your app.pendo.io subscription settings
	Subcription Settings > {Name of your Android App} App Details > Install Settings > Scroll to Step 3
	Look for something formatted like so: String pendoAppKey = ("HUGE GIANT STRING"). This is your API Key
	Copy the API Key in its entirety
Go back to Android Studio
	Paste the full API Key where it specifies on line 38 (within the double quotes)

//////////////////////////////////////////////////
//// DEPRECATING OR UPDATING YOUR SDK VERSION ////
//////////////////////////////////////////////////

If you wish to update and/or deprecate your Pendo SDK, you will want to do the following within Android Studio

In the gradle file, change the dependency to the latest SDK version. Copy the version number from the "dependencies" statement (found in Step 1 of the Install Settings).
	Path to the gradle file is Android > Gradle Scripts > build.gradle (Module: app)
	Go to line 67, here you can change the SDK version where it states 'version: {version number}'

Once changed, resync the Android Project



/////////////////////////
//// IMPORTANT NOTES ////
/////////////////////////

Additional Firebase data will be soon available for use as metaData key::values