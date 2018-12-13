#ifndef CONFIG_H
#define CONFIG_H

#include "DataStructure.h"

namespace config {
	static const int defaultWindowSize = 10;
	static const int defaultSeqNumBits = 6;

	static const int defaultTCPSeqNumBits = 32;
	static const int defaultTCPAckNumBits = 32;

	static const int defaultClkSeqnum = 0;
}


#endif // !CONFIG_H
