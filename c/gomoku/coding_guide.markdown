[TOC]

# Platform

  For now, this guide is only for the engineers who work on MAC OS.

# How to read this guide?

  This is a markdown file, which is good for read and edit. There is a series of operations below to help you configuring it.

  - Install Sublime2.

    Sublime3 is available now, but we have not tested it yet. So Sublime2 is still the recommended item. The following guide is only for sublime2.

    Then reopen this guid with Sublime, which you had installed just now.

  - Install Package Control for Sublime.

    1. Press ctrl+` shortcut or open View->Show Console menu item to open the text console of Sublime.

    2. Paste the following codes into the console and run them.

            import urllib2,os,hashlib; h = 'df21e130d211cfc94d9b0905775a7c0f' + '1e3d39e33b79698005270310898eea76'; pf = 'Package Control.sublime-package'; ipp = sublime.installed_packages_path(); os.makedirs( ipp ) if not os.path.exists(ipp) else None; urllib2.install_opener( urllib2.build_opener( urllib2.ProxyHandler()) ); by = urllib2.urlopen( 'http://packagecontrol.io/' + pf.replace(' ', '%20')).read(); dh = hashlib.sha256(by).hexdigest(); open( os.path.join( ipp, pf), 'wb' ).write(by) if dh == h else None; print('Error validating download (got %s instead of %s), please try manual install' % (dh, h) if dh != h else 'Please restart Sublime Text to finish installation')

        When the actions finish, you should restart Sublime and reopen this guide.

  - Install Markdown Preview plugin.

    Press command+shift+p to open Command Pallete. Input "pci" in the textbox, which has popped up just now. Select the "Package Control: Install Package". Now, there is another textbox popped up. Input "Markdown Preview" and select it. Then wait for the installation.

    After the installation, it's better to restart Sublime. Then reopen this guide. Now, open the Command Pallete via command+shift+p and input "mppb" in the popped textbox. Select the item which name is like "... in Browser" and then choose "markdown", then you'll see this guide via a graceful way.

    NOTE: If you cannot find anything after typing "mppb", you can try "ssm"(Set Syntax: Markdown) first. Then try "mppb" again.

# Build and Run

  At the first, you should enter the directory of gomoku project via MAC terminal. Then you could do the following things.

  - Rebuild

    Command line:

        make rebuild

    This will rebuild entire gomoku project. Cause by we write the makefile very simply, rebuilding is recommended way to build this project.

  - Build

    Command line:

        make

    It is a bad way to build this project. Do it only when you konw the mechanism of makefile well and what you are doing.

  - Clean

    Command line:

        make clean

    Clean all outputs, which includes the final binaries, of this project.

  - Run

    Command line:

        make run

    This is the easy way to run the final binaries.
