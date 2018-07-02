package org.phoebus.applications.filebrowser;

import java.io.File;
import java.net.URI;

import org.phoebus.framework.preferences.PreferencesReader;
import org.phoebus.framework.spi.AppInstance;
import org.phoebus.framework.spi.AppResourceDescriptor;

@SuppressWarnings("nls")
public class FileBrowserApp implements AppResourceDescriptor {

    public static final String Name = "file_browser";

    public static final String DisplayName = "File Browser";

    /** Initial root directory for newly opened file browser */
    public static final File default_root;

    static
    {
        final PreferencesReader prefs = new PreferencesReader(FileBrowserApp.class, "/filebrowser_preferences.properties");
        default_root = new File(PreferencesReader.replaceProperties(prefs.get("default_root")));
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public String getDisplayName()
    {
        return DisplayName;
    }

    @Override
    public AppInstance create() {
        return createWithRoot(default_root);
    }

    @Override
    public AppInstance create(final URI resource)
    {
        return createWithRoot(new File(resource));
    }

    public AppInstance createWithRoot(final File directory)
    {
        return new FileBrowser(this, directory);
    }
}
