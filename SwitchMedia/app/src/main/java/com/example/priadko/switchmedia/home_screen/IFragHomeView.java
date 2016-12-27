package com.example.priadko.switchmedia.home_screen;

/**
 * SwitchMedia
 * Oleksandr Priadko
 */

public interface IFragHomeView {
    /**
     * Data from server loaded or updated
     */
    void dataLoaded(String[][] data);

    /**
     * Loading started
     */
    void loadingStarted();

    void showDetailScreen();

    void hideDetailScreen();

    void setDetailScreenTitle(String title);

    void setDetailScreenImage(String imageUrl);
}
