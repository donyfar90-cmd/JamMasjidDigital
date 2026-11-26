Jam Masjid Digital V2 - Web assets bundle
Replace files in app/src/main/assets:
- index.html (use the index V2 provided in conversation)
- style.css (this bundle)
- app.js (this bundle)
Admin page: /admin (or ?admin)\nLocal settings save to browser localStorage and images to IndexedDB.\nImportant: the existing Android local web server must serve the same assets and support /admin path alias. You may need to update MainActivity/server routing if /admin does not return index.html.
