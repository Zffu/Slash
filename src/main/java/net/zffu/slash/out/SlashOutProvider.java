package net.zffu.slash.out;

public enum SlashOutProvider {

    ;

    public final OutConstruct<SlashWriteProvider> provider;

    SlashOutProvider(OutConstruct<SlashWriteProvider> provider) {
        this.provider = provider;
    }

}
