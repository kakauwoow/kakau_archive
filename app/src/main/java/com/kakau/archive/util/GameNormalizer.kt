package com.kakau.archive.util

object GameNormalizer {
 fun normalize(input: String): String = input.lowercase()
  .replace("&", "and")
  .replace(Regex("""\.(jpg|jpeg|png|webp|gif|bmp|avif)$"""), "")
  .replace(Regex("""\b(game|games|cover|poster|box|art|boxart|edition)\b"""), "")
  .replace(Regex("[^a-z0-9]"), "")
}
