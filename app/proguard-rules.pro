-optimizationpasses 5

-dontusemixedcaseclassnames

-dontskipnonpubliclibraryclasses

-dontpreverify

-verbose

-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class org.jsoup.** {
 }

-ignorewarnings
