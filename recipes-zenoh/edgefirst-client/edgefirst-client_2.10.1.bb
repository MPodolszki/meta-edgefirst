SUMMARY = "EdgeFirst Client SDK"
HOMEPAGE = "https://pypi.org/project/edgefirst-client/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

WHEEL_FILE[aarch64] = "edgefirst_client-${PV}-cp38-abi3-manylinux_2_17_aarch64.manylinux2014_aarch64.whl"
WHEEL_FILE[x86_64] = "edgefirst_client-${PV}-cp38-abi3-manylinux_2_17_x86_64.manylinux2014_x86_64.whl"

WHEEL_URL[aarch64] = "https://files.pythonhosted.org/packages/c3/94/c977550a8d559cd2be507207b8528d29c12737595b82e2f494203ff948a5/edgefirst_client-2.10.1-cp38-abi3-manylinux_2_17_aarch64.manylinux2014_aarch64.whl"
WHEEL_URL[x86_64] = "https://files.pythonhosted.org/packages/21/7a/32dc6862ed78a4802e323446f70d6cc9a7dbd8b4d2f2b14a7d87ec7e872a/edgefirst_client-2.10.1-cp38-abi3-manylinux_2_17_x86_64.manylinux2014_x86_64.whl"

WHEEL_SHA256[aarch64] = "ed0b6b421d78aff75f2bdfaf0fec5420d2e2c24f0a670abf364926defdfa749e"
WHEEL_SHA256[x86_64] = "7eb8de769644e8bcfb36da7bfe3b6b6118a51cc08db88f6615d7caf5481fe36b"

S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

inherit python3-dir python3native

DEPENDS += "python3-installer-native"
RDEPENDS:${PN} += "python3-core"

python __anonymous() {
    arch = d.getVar('TARGET_ARCH')
    wheel_file = d.getVarFlag('WHEEL_FILE', arch)
    wheel_url = d.getVarFlag('WHEEL_URL', arch)
    wheel_sha = d.getVarFlag('WHEEL_SHA256', arch)

    if not wheel_file or not wheel_url or not wheel_sha:
        bb.fatal('Unsupported TARGET_ARCH for edgefirst-client: %s' % arch)

    d.setVar('WHEEL_FILENAME', wheel_file)
    d.setVar('SRC_URI', '%s;downloadfilename=%s' % (wheel_url, wheel_file))
    d.setVarFlag('SRC_URI', 'sha256sum', wheel_sha)
}

do_install() {
    nativepython3 -m installer --destdir=${D} --prefix=${prefix} ${S}/${WHEEL_FILENAME}
}

FILES:${PN} += "${bindir} ${PYTHON_SITEPACKAGES_DIR}"
