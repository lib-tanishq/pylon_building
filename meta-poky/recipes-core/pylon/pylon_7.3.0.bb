SUMMARY = "Basler Pylon Camera SDK"
DESCRIPTION = "Basler Pylon Camera SDK for working with Basler cameras."
HOMEPAGE = "https://www.baslerweb.com"
LICENSE = "Proprietary"

LIC_FILES_CHKSUM = "file://${WORKDIR}/share/pylon/licenses/License.txt;md5=71fd665b3c1cdeedf72522102cbe2bc0 \
                    file://${WORKDIR}/share/pylon/licenses/pylon_Third-Party_Licenses.txt;md5=6b31a3df23f531635229ec2011955b89"

SRC_URI = "file://pylon-7.3.0.27189_linux-aarch64.tar.gz"

do_install() {
    cd ${WORKDIR}/include
    find . -type d -exec install -d ${D}${includedir}/{} \;
    find . -type f -exec install -m 0644 {} ${D}${includedir}/{} \;

    cd ${WORKDIR}/lib
    find . -type d -exec install -d ${D}${libdir}/{} \;
    cp -rP ${WORKDIR}/lib/* ${D}${libdir}/
    cd ${D}${libdir}
    rm -rf Qt pylonviewer

    install -d ${D}${libdir}/cmake/
    cp -r ${WORKDIR}/share/pylon/cmake/* ${D}${libdir}/cmake/
}

INSANE_SKIP_${PN} += "dev-so"
INSANE_SKIP_${PN} += "already-stripped"

FILES_${PN} += " ${libdir}/ ${includedir}/"
FILES_${PN}-dev = " "
