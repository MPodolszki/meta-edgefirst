SUMMARY = "EdgeFirst AI Inference Client"
HOMEPAGE = "https://pypi.org/project/edgefirst-client/"

LICENSE = "CLOSED"

SRC_URI = "https://files.pythonhosted.org/packages/c3/94/c977550a8d559cd2be507207b8528d29c12737595b82e2f494203ff948a5/edgefirst_client-${PV}-cp38-abi3-manylinux_2_17_aarch64.manylinux2014_aarch64.whl"
SRC_URI[sha256sum] = "ed0b6b421d78aff75f2bdfaf0fec5420d2e2c24f0a670abf364926defdfa749e"

S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

inherit python3-dir

DEPENDS = "python3 python3-pip-native unzip-native"
RDEPENDS:${PN} = "python3"

COMPATIBLE_HOST = "(aarch64.*-linux)"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    unzip ${S}/edgefirst_client-${PV}-cp38-abi3-manylinux_2_17_aarch64.manylinux2014_aarch64.whl -d ${D}${PYTHON_SITEPACKAGES_DIR}
}

do_install[depends] += "unzip-native:do_populate_sysroot"

INSANE_SKIP:${PN} += "ldflags"

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}"
