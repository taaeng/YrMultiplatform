package no.nrk.yr.yrtestkmmregular

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " - Thomoaso - " + UIDevice.currentDevice.systemVersion
}