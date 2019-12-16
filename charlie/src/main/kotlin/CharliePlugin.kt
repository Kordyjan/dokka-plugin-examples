package org.jetbrains.dokka.examples.plugins.charlie

import org.jetbrains.dokka.CoreExtensions
import org.jetbrains.dokka.examples.plugins.alpha.AlphaPlugin
import org.jetbrains.dokka.examples.plugins.bravo.BravoPlugin
import org.jetbrains.dokka.plugability.DokkaPlugin

class CharliePlugin: DokkaPlugin() {
    val a by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension A. Should be before B" order {
            before(plugin<BravoPlugin>().b)
        }
    }

    val c by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension C. Should be before E and after A" order {
            after(a)
            before(plugin<BravoPlugin>().b)
        }
    }

    val f by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension F. Should be after E" order {
            after(plugin<BravoPlugin>().e)
        }
    }
}