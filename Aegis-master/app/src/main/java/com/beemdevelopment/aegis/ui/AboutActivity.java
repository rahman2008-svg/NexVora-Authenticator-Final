package com.beemdevelopment.aegis.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.AttrRes;
import androidx.annotation.StringRes;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.beemdevelopment.aegis.BuildConfig;
import com.beemdevelopment.aegis.R;
import com.beemdevelopment.aegis.ui.dialogs.ChangelogDialog;
import com.beemdevelopment.aegis.ui.dialogs.LicenseDialog;
import com.beemdevelopment.aegis.helpers.ViewHelper;
import com.google.android.material.color.MaterialColors;

public class AboutActivity extends AegisActivity {

    private static final String NX_FACEBOOK = "https://www.facebook.com/share/1BNn32qoJo/";
    private static final String NX_INSTAGRAM = "https://www.instagram.com/ur___abdur____rahman__";
    private static final String NX_GITHUB = "https://github.com/rahman2008-svg";
    private static final String NX_EMAIL = "prince.ar.abdur.rahman2008@gmail.com";

    // Upstream project (GPL-3.0), kept for attribution
    private static String GITHUB = "https://github.com/beemdevelopment/Aegis";
    private static String WEBSITE_ALEXANDER = "https://alexbakker.me";
    private static String GITHUB_MICHAEL = "https://github.com/michaelschattgen";

    private static String MAIL_BEEMDEVELOPMENT = "beemdevelopment@gmail.com";
    private static String WEBSITE_BEEMDEVELOPMENT = "https://beem.dev/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (abortIfOrphan(savedInstanceState)) {
            return;
        }

        setContentView(R.layout.activity_about);
        setSupportActionBar(findViewById(R.id.toolbar));
        ViewHelper.setupAppBarInsets(findViewById(R.id.app_bar_layout));

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        View btnLicense = findViewById(R.id.btn_license);
        btnLicense.setOnClickListener(v -> {
            LicenseDialog.create()
                    .setTheme(_themeHelper.getConfiguredTheme())
                    .show(getSupportFragmentManager(), null);
        });

        View btnThirdPartyLicenses = findViewById(R.id.btn_third_party_licenses);
        btnThirdPartyLicenses.setOnClickListener(v -> {
            Intent intent = new Intent(this, LicensesActivity.class);
            startActivity(intent);
        });

        TextView appVersion = findViewById(R.id.app_version);
        appVersion.setText(getCurrentAppVersion());

        View btnAppVersion = findViewById(R.id.btn_app_version);
        btnAppVersion.setOnClickListener(v -> {
            copyToClipboard(getCurrentAppVersion(), R.string.version_copied);
        });

        findViewById(R.id.nx_whatsapp).setOnClickListener(v ->
                copyToClipboard(getString(R.string.nx_whatsapp_numbers), R.string.nx_whatsapp_copied));
        findViewById(R.id.nx_facebook).setOnClickListener(v -> openUrl(NX_FACEBOOK));
        findViewById(R.id.nx_instagram).setOnClickListener(v -> openUrl(NX_INSTAGRAM));
        findViewById(R.id.nx_github).setOnClickListener(v -> openUrl(NX_GITHUB));
        findViewById(R.id.nx_email).setOnClickListener(v -> openMail(NX_EMAIL));

        View btnGithub = findViewById(R.id.btn_github);
        btnGithub.setOnClickListener(v -> openUrl(GITHUB));

        View btnAlexander = findViewById(R.id.btn_alexander);
        btnAlexander.setOnClickListener(v -> openUrl(WEBSITE_ALEXANDER));

        View btnMichael = findViewById(R.id.btn_michael);
        btnMichael.setOnClickListener(v -> openUrl(GITHUB_MICHAEL));

        View btnMail = findViewById(R.id.btn_email);
        btnMail.setOnClickListener(v -> openMail(MAIL_BEEMDEVELOPMENT));

        View btnWebsite = findViewById(R.id.btn_website);
        btnWebsite.setOnClickListener(v -> openUrl(WEBSITE_BEEMDEVELOPMENT));


        View btnChangelog = findViewById(R.id.btn_changelog);
        btnChangelog.setOnClickListener(v -> {
            ChangelogDialog.create()
                    .setTheme(_themeHelper.getConfiguredTheme())
                    .show(getSupportFragmentManager(), null);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.about_scroll_view), (targetView, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            targetView.setPadding(
                    0,
                    0,
                    0,
                    insets.bottom
            );
            return WindowInsetsCompat.CONSUMED;
        });
    }

    private static String getCurrentAppVersion() {
        if (BuildConfig.DEBUG) {
            return String.format("%s-%s (%s)", BuildConfig.VERSION_NAME, BuildConfig.GIT_HASH, BuildConfig.GIT_BRANCH);
        }

        return BuildConfig.VERSION_NAME;
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(browserIntent);
    }

    private void copyToClipboard(String text, @StringRes int messageId) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("text/plain", text);
        clipboard.setPrimaryClip(data);
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
    }

    private void openMail(String mailaddress) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:" + mailaddress));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, mailaddress);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name_full);

        startActivity(Intent.createChooser(mailIntent, getString(R.string.email)));
    }

    private String getThemeColorAsHex(@AttrRes int attributeId) {
        int color = MaterialColors.getColor(this, attributeId, getClass().getCanonicalName());
        return String.format("%06X", 0xFFFFFF & color);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
