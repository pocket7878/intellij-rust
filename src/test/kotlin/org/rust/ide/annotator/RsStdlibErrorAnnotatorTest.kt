/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.rust.ide.annotator

import com.intellij.testFramework.LightProjectDescriptor
import org.rust.lang.RsTestBase

class RsStdlibErrorAnnotatorTest : RsAnnotatorTestBase() {
    override fun getProjectDescriptor(): LightProjectDescriptor = RsTestBase.WithStdlibAndDependencyRustProjectDescriptor

    fun testE0428_RespectsCrateAliases() = checkErrors("""
        extern crate libc as libc_alias;
        mod libc {}

        // FIXME: ideally we want to highlight these
        extern crate alloc;
        mod alloc {}
    """)

    fun testE0463_UnknownCrate() = checkErrors("""
        extern crate alloc;

        <error descr="Can't find crate for `litarvan` [E0463]">extern crate litarvan;</error>
    """)
}
