SUMMARY = "On-target profiling agent for AI vision pipelines"
HOMEPAGE = "https://pypi.org/project/edgefirst-profiler/"

LICENSE = "CLOSED"

SRC_URI = "https://files.pythonhosted.org/packages/be/5c/ef01130c23031c5ef80f5eae0c521d0bab771fdbeba8bc3a183291b761bb/edgefirst_profiler-${PV}-py3-none-manylinux2014_aarch64.manylinux_2_17_aarch64.whl"
SRC_URI[sha256sum] = "df933c5a20de0e94c275ef85df660ab464be8594ce39d4a471449047665cf647"

S = "${@d.getVar('UNPACKDIR') or d.getVar('WORKDIR')}"

inherit python3-dir

DEPENDS = "python3 python3-pip-native unzip-native"
RDEPENDS:${PN} = "python3"

COMPATIBLE_HOST = "(aarch64.*-linux)"

do_install() {
    install -d ${D}${PYTHON_SITEPACKAGES_DIR}
    unzip ${S}/edgefirst_profiler-${PV}-py3-none-manylinux2014_aarch64.manylinux_2_17_aarch64.whl -d ${D}${PYTHON_SITEPACKAGES_DIR}
}

do_install[depends] += "unzip-native:do_populate_sysroot"

INSANE_SKIP:${PN} += "ldflags"

FILES:${PN} = "${PYTHON_SITEPACKAGES_DIR}"
