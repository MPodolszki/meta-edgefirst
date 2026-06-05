SUMMARY = "EdgeFirst profiling agent"
HOMEPAGE = "https://pypi.org/project/edgefirst-profiler/"

LICENSE = "CLOSED"

WHEEL_FILE[aarch64] = "edgefirst_profiler-${PV}-py3-none-manylinux2014_aarch64.manylinux_2_17_aarch64.whl"
WHEEL_FILE[x86_64] = "edgefirst_profiler-${PV}-py3-none-manylinux2014_x86_64.manylinux_2_17_x86_64.whl"

WHEEL_URL[aarch64] = "https://files.pythonhosted.org/packages/7e/c3/baef1dc6153222a17101467c30f45e4573292e2a3bda891c4d3cc032e7fd/edgefirst_profiler-1.2.0-py3-none-manylinux2014_aarch64.manylinux_2_17_aarch64.whl"
WHEEL_URL[x86_64] = "https://files.pythonhosted.org/packages/8e/0a/1420a867066035334083838dbf4a3f18f195a23fe89da7745d0a2120f49b/edgefirst_profiler-1.2.0-py3-none-manylinux2014_x86_64.manylinux_2_17_x86_64.whl"

WHEEL_SHA256[aarch64] = "c4148474032f2327d88ce560a847fc560b4d868db3aa7b9811d02d0c1d81c2a8"
WHEEL_SHA256[x86_64] = "3ab75697ad907c406ce489ceb044caa249cfb714a4a976c7dd6d251e791b48ee"

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
        bb.fatal('Unsupported TARGET_ARCH for edgefirst-profiler: %s' % arch)

    d.setVar('WHEEL_FILENAME', wheel_file)
    d.setVar('SRC_URI', '%s;downloadfilename=%s' % (wheel_url, wheel_file))
    d.setVarFlag('SRC_URI', 'sha256sum', wheel_sha)
}

do_install() {
    nativepython3 -m installer --destdir=${D} --prefix=${prefix} ${S}/${WHEEL_FILENAME}
}

FILES:${PN} += "${bindir} ${PYTHON_SITEPACKAGES_DIR}"
