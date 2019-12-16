package org.jetbrains.dokka.examples.plugins.delta

import org.jetbrains.dokka.examples.plugins.alpha.AlphaPlugin
import org.jetbrains.dokka.examples.plugins.bravo.BravoPlugin
import org.jetbrains.dokka.examples.plugins.charlie.CharliePlugin
import org.jetbrains.dokka.plugability.DokkaPlugin

class DeltaPlugin: DokkaPlugin() {
    val d by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension D. Should be after A and before F" order {
            after(plugin<CharliePlugin>().a)
            before(plugin<CharliePlugin>().f)
        }
    }

    val g by extending {
        plugin<AlphaPlugin>().debugExtensionMessage with "Extension G. Should be after B, C and D" order {
            after(plugin<BravoPlugin>().b ,plugin<CharliePlugin>().c, d)
        }
    }
}