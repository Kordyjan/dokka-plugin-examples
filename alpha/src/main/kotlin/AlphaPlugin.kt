package org.jetbrains.dokka.examples.plugins.alpha

import org.jetbrains.dokka.CoreExtensions
import org.jetbrains.dokka.model.Module
import org.jetbrains.dokka.pages.ModulePageNode
import org.jetbrains.dokka.pages.PageNode
import org.jetbrains.dokka.plugability.DokkaContext
import org.jetbrains.dokka.plugability.DokkaPlugin
import org.jetbrains.dokka.plugability.single
import org.jetbrains.dokka.transformers.documentation.DocumentationNodeTransformer
import org.jetbrains.dokka.transformers.pages.PageNodeTransformer

class AlphaPlugin: DokkaPlugin() {
    val formatter by extensionPoint<(PageNode) -> String>()

    val debugExtensionMessage by extensionPoint<String>()

    val pagePrinter by extending {
        CoreExtensions.pageTransformer with PagePrinterTransformer()
    }

    val debugExtensionsPrinter by extending {
        CoreExtensions.documentationTransformer with DebugExtensionsPrinter()
    }

    inner class PagePrinterTransformer : PageNodeTransformer {
        override fun invoke(input: ModulePageNode, dokkaContext: DokkaContext): ModulePageNode {
            val formatter = dokkaContext.single(formatter)

            fun visit(page: PageNode): PageNode = page.also {
                dokkaContext.logger.progress(formatter(it))
            }

            return input.transformPageNodeTree { visit(it) }
        }
    }

    inner class DebugExtensionsPrinter: DocumentationNodeTransformer {
        override fun invoke(original: Module, context: DokkaContext): Module = original.also {
            context.logger.run {
                progress("")
                progress("=== Extension ordering debug ===")
                context[debugExtensionMessage].forEach { progress(it) }
                progress("=== ===")
                progress("")
            }
        }
    }
}