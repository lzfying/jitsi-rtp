/*
 * Copyright @ 2018 - present 8x8, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jitsi.rtp

import org.jitsi.rtp.util.BufferPool
import java.nio.ByteBuffer

abstract class Serializable {
    abstract val sizeBytes: Int

    /**
     * Get the contents of this [Serializable] in a [ByteBuffer].
     * Depending on the implementation, the given buffer may
     * be newly allocated or an owned buffer that is reused.
     *
     * The returned buffer should have its position set to
     * 0 and its limit at the end of the serialized data.
     */
    open fun getBuffer(): ByteBuffer {
        val b = BufferPool.getBuffer(sizeBytes)
        serializeTo(b)

        return b.rewind() as ByteBuffer
    }

    /**
     * Serialize the contents of this [Serializable] into
     * the given buffer, starting at its current position.
     *
     * After this method returns, [buf]'s position will
     * be at the end of the data which was just written.
     */
    abstract fun serializeTo(buf: ByteBuffer)
}