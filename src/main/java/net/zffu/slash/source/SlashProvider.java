package net.zffu.slash.source;

public enum SlashProvider {

    ;

    public final Construct<SlashSourceProvider> provider;
    public final int minimumFileCount;

    SlashProvider(Construct<SlashSourceProvider> provider, int minimumFileCount) {
        this.provider = provider;
        this.minimumFileCount = minimumFileCount;
    }

}
